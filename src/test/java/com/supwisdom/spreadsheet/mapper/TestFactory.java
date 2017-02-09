package com.supwisdom.spreadsheet.mapper;

import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.model.meta.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hanwen on 2017/1/4.
 */
public class TestFactory {

  public static TestBean createBean1() {

    TestBean testBean = new TestBean();

    testBean.setInt1(10000);
    testBean.setInt2(-20000);

    testBean.setLong1(10000000000000L);
    testBean.setLong2(20000000000000L);

    testBean.setFloat1(0.001F);
    testBean.setFloat2(0.00000002F);

    testBean.setDouble1(0.00000000000000000001D);
    testBean.setDouble2(0.00000000000000000002D);

    testBean.setBoolean1(true);
    testBean.setBoolean2(false);

    testBean.setString("Scarlett Johansson");

    testBean.setBigDecimal(BigDecimal.valueOf(0.00000000000000000001));

    testBean.setLocalDate(new LocalDate(1984, 11, 22));

    testBean.setLocalDateTime(new LocalDateTime(1984, 11, 22, 0, 0, 0));

    return testBean;
  }

  public static TestBean createBean2() {

    TestBean testBean = new TestBean();

    testBean.setInt1(1);

    testBean.setLong1(1L);

    testBean.setFloat1(0.1F);

    testBean.setDouble1(0.00001D);

    testBean.setString("Scarlett Johansson");

    testBean.setBoolean1(false);

    return testBean;
  }

  public static Map<String, FieldMeta> createFieldMetaMap() {

    Map<String, FieldMeta> fieldMetaMap = new LinkedHashMap<>();

    fieldMetaMap.put("int1", new FieldMetaBean("int1", 1));
    fieldMetaMap.put("int2", new FieldMetaBean("int2", 2));
    fieldMetaMap.put("long1", new FieldMetaBean("long1", 3));
    fieldMetaMap.put("long2", new FieldMetaBean("long2", 4));
    fieldMetaMap.put("float1", new FieldMetaBean("float1", 5));
    fieldMetaMap.put("float2", new FieldMetaBean("float2", 6));
    fieldMetaMap.put("double1", new FieldMetaBean("double1", 7));
    fieldMetaMap.put("double2", new FieldMetaBean("double2", 8));
    fieldMetaMap.put("boolean1", new FieldMetaBean("boolean1", 9));
    fieldMetaMap.put("boolean2", new FieldMetaBean("boolean2", 10));
    fieldMetaMap.put("string", new FieldMetaBean("string", 11));
    fieldMetaMap.put("bigDecimal", new FieldMetaBean("bigDecimal", 12));
    fieldMetaMap.put("localDate", new FieldMetaBean("localDate", 13));
    fieldMetaMap.put("localDateTime", new FieldMetaBean("localDateTime", 14));

    return fieldMetaMap;
  }

  public static Map<String, Cell> createCellMap1() {

    Map<String, Cell> cellMap = new LinkedHashMap<>();

    cellMap.put("int1", new CellBean("10000"));
    cellMap.put("int2", new CellBean("-20000"));
    cellMap.put("long1", new CellBean("10000000000000"));
    cellMap.put("long2", new CellBean("20000000000000"));
    cellMap.put("float1", new CellBean("0.001"));
    cellMap.put("float2", new CellBean("0.00000002"));
    cellMap.put("double1", new CellBean("0.00000000000000000001"));
    cellMap.put("double2", new CellBean("0.00000000000000000002"));
    cellMap.put("boolean1", new CellBean("pass"));
    cellMap.put("boolean2", new CellBean("failure"));
    cellMap.put("string", new CellBean("Scarlett Johansson"));
    cellMap.put("bigDecimal", new CellBean("1.0E-20"));
    cellMap.put("localDate", new CellBean("1984-11-22"));
    cellMap.put("localDateTime", new CellBean("1984-11-22 00:00:00"));

    return cellMap;
  }

  public static Map<String, Cell> createCellMap2() {

    Map<String, Cell> cellMap = new LinkedHashMap<>();

    cellMap.put("int1", new CellBean("1"));
    cellMap.put("int2", new CellBean());
    cellMap.put("long1", new CellBean("1"));
    cellMap.put("long2", new CellBean());
    cellMap.put("float1", new CellBean("0.1"));
    cellMap.put("float2", new CellBean());
    cellMap.put("double1", new CellBean("0.00001"));
    cellMap.put("double2", new CellBean());
    cellMap.put("boolean1", new CellBean("failure"));
    cellMap.put("boolean2", new CellBean());
    cellMap.put("string", new CellBean("Scarlett Johansson"));
    cellMap.put("bigDecimal", new CellBean());
    cellMap.put("localDate", new CellBean());
    cellMap.put("localDateTime", new CellBean());

    return cellMap;
  }

  public static Map<String, Cell> createErrorCellMap() {

    Map<String, Cell> cellMap = new LinkedHashMap<>();

    cellMap.put("int1", new CellBean("dasdasd"));
    cellMap.put("int2", new CellBean(""));
    cellMap.put("long1", new CellBean("afsdfasdf"));
    cellMap.put("long2", new CellBean(""));
    cellMap.put("float1", new CellBean("0.asfadsf"));
    cellMap.put("float2", new CellBean(""));
    cellMap.put("double1", new CellBean("0.345dfasd"));
    cellMap.put("double2", new CellBean(""));
    cellMap.put("boolean1", new CellBean("t"));
    cellMap.put("boolean2", new CellBean(""));
    cellMap.put("string", new CellBean("Scarlett Johansson"));
    cellMap.put("bigDecimal", new CellBean("0.00000000000000000001"));
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
