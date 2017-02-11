package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.FieldConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.Converter;
import com.supwisdom.spreadsheet.mapper.o2w.converter.BeanUtilsConverter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanwen on 15-12-16.
 */
public class DefaultObject2SheetComposer<T> implements Object2SheetComposer<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultObject2SheetComposer.class);

  private LinkedHashMap<String, FieldConverter<T>> field2converter = new LinkedHashMap<>();

  private Converter<T> defaultConverter = new BeanUtilsConverter<>();

  @Override
  public Object2SheetComposer<T> addFieldConverter(FieldConverter<T> fieldConverter) {
    if (fieldConverter == null) {
      throw new IllegalArgumentException("field converter can not be null");
    }

    String matchField = fieldConverter.getMatchField();
    if (StringUtils.isBlank(matchField)) {
      throw new IllegalArgumentException("field value setter match field can not be null");
    }
    if (field2converter.containsKey(matchField)) {
      throw new IllegalArgumentException("sheet compose helper contains multi field converter at field[" + matchField + "]");
    }

    field2converter.put(matchField, fieldConverter);
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
    List<FieldMeta> fieldMetas = sheetMeta.getName2FieldMeta();

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

    List<FieldMeta> fieldMetas = sheetMeta.getName2FieldMeta();
    int lastColumnNum = getMaxColNum(fieldMetas);
    Map<Integer, FieldMeta> columnIndex2fieldMeta = buildFieldMetaMap(fieldMetas);

    for (int i = 1; i <= lastColumnNum; i++) {
      Cell cell = new CellBean();
      FieldMeta fieldMeta = columnIndex2fieldMeta.get(i);

      if (fieldMeta == null) {
        LOGGER.debug("no field meta at column index:[" + i + "], will create an empty cell");

        row.addCell(cell);
        continue;
      }

      // use default converter first
      cell = new CellBean(defaultConverter.getValue(object, cell, fieldMeta));

      FieldConverter<T> converter = field2converter.get(fieldMeta.getName());

      if (converter != null) {
        cell = new CellBean(converter.getValue(object, cell, fieldMeta));
      }

      row.addCell(cell);
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
