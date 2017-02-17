package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.DefaultPropertyStringifier;
import com.supwisdom.spreadsheet.mapper.o2w.converter.PropertyStringifier;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanwen on 15-12-16.
 */
public class DefaultObject2SheetComposer<T> implements Object2SheetComposer<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultObject2SheetComposer.class);

  private LinkedHashMap<String, PropertyStringifier<T>> field2Converter = new LinkedHashMap<>();

  private DefaultPropertyStringifier defaultToStringConverter = new DefaultPropertyStringifier();

  @Override
  public Object2SheetComposer<T> addFieldConverter(PropertyStringifier propertyStringifier) {
    if (propertyStringifier == null) {
      throw new IllegalArgumentException("field converter can not be null");
    }

    String matchField = propertyStringifier.getMatchField();
    if (StringUtils.isBlank(matchField)) {
      throw new IllegalArgumentException("field value setter match field can not be null");
    }
    if (field2Converter.containsKey(matchField)) {
      throw new IllegalArgumentException(
          "sheet compose helper contains multi field converter at field[" + matchField + "]");
    }

    field2Converter.put(matchField, propertyStringifier);
    return this;
  }

  @Override
  public Sheet compose(List<T> dataOfSheet, SheetMeta sheetMeta) {

    Sheet sheet = createSheet(sheetMeta);

    int dataStartRowIndex = sheetMeta.getDataStartRowIndex();
    for (int i = 1; i < dataStartRowIndex; i++) {

      Row row = createRow();
      sheet.addRow(row);
      createHeaderCellsIfNecessary(row, sheetMeta);
    }

    if (CollectionUtils.isEmpty(dataOfSheet)) {
      return sheet;
    }

    for (T object : dataOfSheet) {
      Row row = createRow();
      sheet.addRow(row);
      createDataCells(object, row, sheetMeta);
    }

    return sheet;
  }

  private Sheet createSheet(SheetMeta sheetMeta) {
    String sheetName = sheetMeta.getSheetName();

    if (StringUtils.isBlank(sheetName)) {
      return new SheetBean();
    }
    return new SheetBean(sheetName);
  }

  private Row createRow() {
    return new RowBean();
  }

  private void createHeaderCellsIfNecessary(Row row, SheetMeta sheetMeta) {
    List<FieldMeta> fieldMetas = sheetMeta.getFieldMetas();

    int lastColumnNum = getMaxColNum(fieldMetas);
    Map<Integer, FieldMeta> columnIndex2fieldMeta = buildFieldMetaMap(fieldMetas);

    for (int i = 1; i <= lastColumnNum; i++) {
      Cell cell;
      FieldMeta fieldMeta = columnIndex2fieldMeta.get(i);

      if (fieldMeta == null) {
        LOGGER.debug("no field meta at column index:[" + i + "], will create a empty cell");

        cell = new CellBean();
        row.addCell(cell);
        continue;
      }

      HeaderMeta headerMeta = fieldMeta.getHeaderMeta(row.getIndex());
      if (headerMeta == null) {
        LOGGER.debug("no header meta at row index:[" + row.getIndex() + "], will create an empty cell");

        cell = new CellBean();
        row.addCell(cell);
        continue;
      }

      cell = new CellBean(headerMeta.getValue());
      row.addCell(cell);
    }

  }

  private void createDataCells(T object, Row row, SheetMeta sheetMeta) {

    List<FieldMeta> fieldMetas = sheetMeta.getFieldMetas();
    int lastColumnNum = getMaxColNum(fieldMetas);
    Map<Integer, FieldMeta> columnIndex2fieldMeta = buildFieldMetaMap(fieldMetas);

    for (int i = 1; i <= lastColumnNum; i++) {

      if (object == null) {
        LOGGER.debug("data object null, create an empty cell");
        row.addCell(new CellBean());
        continue;
      }

      FieldMeta fieldMeta = columnIndex2fieldMeta.get(i);

      if (fieldMeta == null) {
        LOGGER.debug("No field meta for column[{}], create an empty cell", i);
        row.addCell(new CellBean());
        continue;
      }

      String fieldName = fieldMeta.getName();

      PropertyStringifier converter = field2Converter.get(fieldName);
      converter = converter == null ? defaultToStringConverter : converter;
      row.addCell(new CellBean(converter.getPropertyString(object, fieldMeta)));

    }
  }

  private int getMaxColNum(List<FieldMeta> fieldMetas) {
    if (CollectionUtils.isEmpty(fieldMetas)) {
      return 0;
    }
    FieldMeta lastFieldMeta = fieldMetas.get(fieldMetas.size() - 1);
    return lastFieldMeta.getColumnIndex();
  }

  private Map<Integer, FieldMeta> buildFieldMetaMap(List<FieldMeta> fieldMetas) {
    Map<Integer, FieldMeta> columnIndex2fieldMeta = new HashMap<>();
    for (FieldMeta fieldMeta : fieldMetas) {
      columnIndex2fieldMeta.put(fieldMeta.getColumnIndex(), fieldMeta);
    }
    return columnIndex2fieldMeta;
  }
}
