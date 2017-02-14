package com.supwisdom.spreadsheet.mapper;

import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.model.meta.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by qianjia on 2017/2/14.
 */
public class TestFactory {

  public static TestBean createBean1() {

    TestBean testBean = new TestBean();

    testBean.setLocalDate(new LocalDate(1984, 11, 22));

    testBean.setLocalDateTime(new LocalDateTime(1984, 11, 22, 0, 0, 0));

    return testBean;
  }

  public static TestBean createBean2() {

    TestBean testBean = new TestBean();

    return testBean;
  }

  public static Map<String, FieldMeta> createFieldMetaMap() {

    Map<String, FieldMeta> fieldMetaMap = new LinkedHashMap<>();

    fieldMetaMap.put("localDate", new FieldMetaBean("localDate", 13));
    fieldMetaMap.put("localDateTime", new FieldMetaBean("localDateTime", 14));

    return fieldMetaMap;
  }

  public static Map<String, Cell> createCellMap1() {

    Map<String, Cell> cellMap = new LinkedHashMap<>();

    cellMap.put("localDate", new CellBean("1984-11-22"));
    cellMap.put("localDateTime", new CellBean("1984-11-22 00:00:00"));

    return cellMap;
  }

  public static Map<String, Cell> createCellMap2() {

    Map<String, Cell> cellMap = new LinkedHashMap<>();

    cellMap.put("localDate", new CellBean());
    cellMap.put("localDateTime", new CellBean());

    return cellMap;
  }

  public static Map<String, Cell> createErrorCellMap() {

    Map<String, Cell> cellMap = new LinkedHashMap<>();

    cellMap.put("localDate", new CellBean("1984/11/22"));
    cellMap.put("localDateTime", new CellBean("fsadfsadf"));

    return cellMap;
  }

  public static Sheet createSheet() {

    Sheet sheet = new SheetBean();

    Row r1 = new RowBean();
    Row r2 = new RowBean();
    Row r3 = new RowBean();
    sheet.addRow(r1);
    sheet.addRow(r2);
    sheet.addRow(r3);

    Map<String, FieldMeta> fieldMetaMap = createFieldMetaMap();
    for (FieldMeta fieldMeta : fieldMetaMap.values()) {
      r1.addCell(new CellBean(fieldMeta.getName()));
    }

    Map<String, Cell> cellMap1 = createCellMap1();
    for (Cell cell : cellMap1.values()) {
      r2.addCell(cell);
    }
    Map<String, Cell> cellMap2 = createCellMap2();
    for (Cell cell : cellMap2.values()) {
      r3.addCell(cell);
    }

    return sheet;
  }

  public static Workbook createWorkbook() {
    Workbook workbook = new WorkbookBean();

    workbook.addSheet(createSheet());

    return workbook;
  }

  public static SheetMeta createSheetMeta(boolean withHeader) {

    SheetMeta sheetMeta = new SheetMetaBean(2);
    Map<String, FieldMeta> fieldMetaMap = createFieldMetaMap();

    for (FieldMeta fieldMeta : fieldMetaMap.values()) {
      if (withHeader) {
        fieldMeta.addHeaderMeta(new HeaderMetaBean(1, fieldMeta.getName()));
      }
      sheetMeta.addFieldMeta(fieldMeta);
    }

    return sheetMeta;
  }

}
