package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.listener.*;
import com.supwisdom.spreadsheet.mapper.w2o.setter.DefaultPropertySetter;
import com.supwisdom.spreadsheet.mapper.w2o.setter.PropertySetter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 默认的{@link Sheet2ObjectComposer}
 * Created by hanwen on 15-12-16.
 */
public class DefaultSheet2ObjectComposer<T> implements Sheet2ObjectComposer<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSheet2ObjectComposer.class);

  private ObjectFactory<T> objectFactory;

  private SheetProcessListener<T> sheetProcessListener = new NoopSheetProcessListener<>();

  private RowProcessListener<T> rowProcessListener = new NoopRowProcessListener<>();

  private CellProcessListener<T> cellProcessListener = new NoopCellProcessListener<>();

  private LinkedHashMap<String, PropertySetter<T>> field2PropertySetter = new LinkedHashMap<>();

  private DefaultPropertySetter defaultPropertySetter = new DefaultPropertySetter();

  @Override
  public Sheet2ObjectComposer<T> addFieldSetter(PropertySetter propertySetter) {
    if (propertySetter == null) {
      throw new IllegalArgumentException("field setter can not be null");
    }

    String matchField = propertySetter.getMatchField();
    if (StringUtils.isBlank(matchField)) {
      throw new IllegalArgumentException("field value setter match field can not be null");
    }
    if (field2PropertySetter.containsKey(matchField)) {
      throw new IllegalArgumentException(
          "sheet process helper contains multi field setter at field[" + matchField + "]");
    }

    field2PropertySetter.put(matchField, propertySetter);
    return this;
  }

  @Override
  public Sheet2ObjectComposer<T> setObjectFactory(ObjectFactory<T> objectFactory) {
    if (objectFactory == null) {
      throw new IllegalArgumentException("object factory can not be null");
    }

    this.objectFactory = objectFactory;
    return this;
  }

  @Override
  public Sheet2ObjectComposer<T> setSheetProcessorListener(SheetProcessListener<T> sheetProcessListener) {
    if (sheetProcessListener == null) {
      throw new IllegalArgumentException("sheet process listener can not be null");
    }

    this.sheetProcessListener = sheetProcessListener;
    return this;
  }

  @Override
  public Sheet2ObjectComposer<T> setRowProcessorListener(RowProcessListener<T> rowProcessListener) {
    if (rowProcessListener == null) {
      throw new IllegalArgumentException("row process listener can not be null");
    }

    this.rowProcessListener = rowProcessListener;
    return this;
  }

  @Override
  public Sheet2ObjectComposer<T> setCellProcessorListener(CellProcessListener<T> cellProcessListener) {
    if (cellProcessListener == null) {
      throw new IllegalArgumentException("cell process listener can not be null");
    }

    this.cellProcessListener = cellProcessListener;
    return this;
  }

  /**
   * 执行顺序是这样的：
   * <ol>
   *   <li>通知{@link SheetProcessListener}开始</li>
   *   <li>开始遍历Row</li>
   *   <li>&nbsp;&nbsp;调用{@link ObjectFactory}获得要被设置值的Object</li>
   *   <li>&nbsp;&nbsp;通知{@link RowProcessListener}开始</li>
   *   <li>&nbsp;&nbsp;开始遍历Cell</li>
   *   <li>&nbsp;&nbsp;&nbsp;&nbsp;找到对应的FieldMeta</li>
   *   <li>&nbsp;&nbsp;&nbsp;&nbsp;通知{@link CellProcessListener}开始</li>
   *   <li>&nbsp;&nbsp;&nbsp;&nbsp;调用{@link PropertySetter}给Object的某个field设置值</li>
   *   <li>&nbsp;&nbsp;&nbsp;&nbsp;通知{@link CellProcessListener}结束</li>
   *   <li>&nbsp;&nbsp;结束遍历Cell</li>
   *   <li>结束遍历Row，通知{@link RowProcessListener}结束</li>
   *   <li>通知{@link SheetProcessListener}结束</li>
   * </ol>
   */
  @Override
  public List<T> compose(Sheet sheet, SheetMeta sheetMeta) {
    if (objectFactory == null) {
      throw new Workbook2ObjectComposeException("setProperty object factory first");
    }
    assertNoDuplicatedFieldMeta(sheetMeta);

    List<FieldMeta> fieldMetas = sheetMeta.getFieldMetas();
    Map<Integer, FieldMeta> columnIndex2fieldMeta = buildFieldMetaMap(fieldMetas);

    List<T> dataOfSheet = new ArrayList<>();
    sheetProcessListener.before(sheet, sheetMeta);

    for (int i = sheetMeta.getDataStartRowIndex(); i <= sheet.sizeOfRows(); i++) {
      Row row = sheet.getRow(i);

      T object = objectFactory.create(row, sheetMeta);

      rowProcessListener.before(object, row, sheetMeta);

      for (Cell cell : row.getCells()) {

        FieldMeta fieldMeta = columnIndex2fieldMeta.get(cell.getIndex());

        if (fieldMeta == null) {
          // if missing field meta skip the cell(same column index with field meta)
          LOGGER.debug(
              "no field meta at row index:[" + cell.getIndex() + "], cell value:[" + cell.getValue() + "] ignored");
          continue;
        }

        cellProcessListener.before(object, cell, fieldMeta);

        PropertySetter<T> propertySetter = field2PropertySetter.get(fieldMeta.getName());

        if (propertySetter != null) {
          propertySetter.setProperty(object, cell, fieldMeta);
        } else {
          defaultPropertySetter.setProperty(object, cell, fieldMeta);
        }

        cellProcessListener.after(object, cell, fieldMeta);
      }

      rowProcessListener.after(object, row, sheetMeta);

      dataOfSheet.add(object);
    }

    sheetProcessListener.after(dataOfSheet, sheet, sheetMeta);

    return dataOfSheet;
  }

  private Map<Integer, FieldMeta> buildFieldMetaMap(List<FieldMeta> fieldMetas) {
    Map<Integer, FieldMeta> columnIndex2fieldMeta = new HashMap<>();
    for (FieldMeta fieldMeta : fieldMetas) {
      columnIndex2fieldMeta.put(fieldMeta.getColumnIndex(), fieldMeta);
    }
    return columnIndex2fieldMeta;
  }

  /**
   * 检查不存在相同name的FieldMeta
   *
   * @param sheetMeta
   */
  private void assertNoDuplicatedFieldMeta(SheetMeta sheetMeta) {
    List<FieldMeta> fieldMetas = sheetMeta.getFieldMetas();
    Set<String> fieldNames = new HashSet<>(fieldMetas.size());
    for (FieldMeta fieldMeta : fieldMetas) {
      if (!fieldNames.add(fieldMeta.getName())) {
        throw new Workbook2ObjectComposeException("SheetMeta contains duplicate FieldMeta [" + fieldMeta.getName() + "]");
      }
    }
  }

}
