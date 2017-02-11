package com.supwisdom.spreadsheet.mapper;

import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by hanwen on 2017/1/5.
 */
public class AssertUtil {

  private AssertUtil() {
    // default constructor
  }

  public static void assertWorkbookEquals(Workbook workbook, boolean hasHeader) {

    assertEquals(workbook.sizeOfSheets(), 1);

    Sheet sheet = workbook.getSheet(1);

    assertEquals(sheet.getIndex(), 1);

    assertSheetEquals(sheet, hasHeader);

  }

  public static void assertSheetEquals(Sheet sheet, boolean hasHeader) {

    assertEquals(sheet.sizeOfRows(), 3);

    Row r1 = sheet.getRow(1);
    Row r2 = sheet.getRow(2);
    Row r3 = sheet.getRow(3);

    assertHeaderRowEquals(r1, hasHeader);
    assertRow2Equals(r2);
    assertRow3Equals(r3);

  }

  public static void assertHeaderRowEquals(Row r1, boolean hasHeader) {
    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    assertEquals(r1.getCell(1).getValue(), hasHeader ? fieldMetaMap.get("int1").getName() : null);
    assertEquals(r1.getCell(2).getValue(), hasHeader ? fieldMetaMap.get("int2").getName() : null);
    assertEquals(r1.getCell(3).getValue(), hasHeader ? fieldMetaMap.get("long1").getName() : null);
    assertEquals(r1.getCell(4).getValue(), hasHeader ? fieldMetaMap.get("long2").getName() : null);
    assertEquals(r1.getCell(5).getValue(), hasHeader ? fieldMetaMap.get("float1").getName() : null);
    assertEquals(r1.getCell(6).getValue(), hasHeader ? fieldMetaMap.get("float2").getName() : null);
    assertEquals(r1.getCell(7).getValue(), hasHeader ? fieldMetaMap.get("double1").getName() : null);
    assertEquals(r1.getCell(8).getValue(), hasHeader ? fieldMetaMap.get("double2").getName() : null);
    assertEquals(r1.getCell(9).getValue(), hasHeader ? fieldMetaMap.get("boolean1").getName() : null);
    assertEquals(r1.getCell(10).getValue(), hasHeader ? fieldMetaMap.get("boolean2").getName() : null);
    assertEquals(r1.getCell(11).getValue(), hasHeader ? fieldMetaMap.get("string").getName() : null);
    assertEquals(r1.getCell(12).getValue(), hasHeader ? fieldMetaMap.get("bigDecimal").getName() : null);
    assertEquals(r1.getCell(13).getValue(), hasHeader ? fieldMetaMap.get("localDate").getName() : null);
    assertEquals(r1.getCell(14).getValue(), hasHeader ? fieldMetaMap.get("localDateTime").getName() : null);
  }

  public static void assertRow2Equals(Row r2) {
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();

    assertEquals(r2.getCell(1).getValue(), cellMap1.get("int1").getValue());
    assertEquals(r2.getCell(2).getValue(), cellMap1.get("int2").getValue());
    assertEquals(r2.getCell(3).getValue(), cellMap1.get("long1").getValue());
    assertEquals(r2.getCell(4).getValue(), cellMap1.get("long2").getValue());
    assertEquals(r2.getCell(5).getValue(), cellMap1.get("float1").getValue());
    assertEquals(r2.getCell(6).getValue(), cellMap1.get("float2").getValue());
    assertEquals(r2.getCell(7).getValue(), cellMap1.get("double1").getValue());
    assertEquals(r2.getCell(8).getValue(), cellMap1.get("double2").getValue());
    assertEquals(r2.getCell(9).getValue(), cellMap1.get("boolean1").getValue());
    assertEquals(r2.getCell(10).getValue(), cellMap1.get("boolean2").getValue());
    assertEquals(r2.getCell(11).getValue(), cellMap1.get("string").getValue());
    assertEquals(r2.getCell(12).getValue(), cellMap1.get("bigDecimal").getValue());
    assertEquals(r2.getCell(13).getValue(), cellMap1.get("localDate").getValue());
    assertEquals(r2.getCell(14).getValue(), cellMap1.get("localDateTime").getValue());
  }

  public static void assertRow3Equals(Row r3) {
    Map<String, Cell> cellMap2 = TestFactory.createCellMap2();
    assertEquals(r3.getCell(1).getValue(), cellMap2.get("int1").getValue());
    assertEquals(r3.getCell(2).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(3).getValue(), cellMap2.get("long1").getValue());
    assertEquals(r3.getCell(4).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(5).getValue(), cellMap2.get("float1").getValue());
    assertEquals(r3.getCell(6).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(7).getValue(), cellMap2.get("double1").getValue());
    assertEquals(r3.getCell(8).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(9).getValue(), cellMap2.get("boolean1").getValue());
    assertEquals(r3.getCell(10).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(11).getValue(), cellMap2.get("string").getValue());
    assertEquals(r3.getCell(12).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(13).getValue(), new CellBean().getValue());
    assertEquals(r3.getCell(14).getValue(), new CellBean().getValue());
  }

  public static void assertTestBean1Equals(TestBean testBean) {
    assertEquals(testBean.getInt1(), 10000);
    assertEquals(testBean.getInt2(), new Integer(-20000));
    assertEquals(testBean.getLong1(), 10000000000000L);
    assertEquals(testBean.getLong2(), new Long(20000000000000L));
    assertEquals(testBean.getFloat1(), 0.001F);
    assertEquals(testBean.getFloat2(), 0.00000002F);
    assertEquals(testBean.getDouble1(), 0.00000000000000000001D);
    assertEquals(testBean.getDouble2(), 0.00000000000000000002D);
    assertEquals(testBean.getString(), "Scarlett Johansson");
    assertEquals(testBean.getLocalDate(), new LocalDate(1984, 11, 22));
    assertEquals(testBean.getLocalDateTime(), new LocalDateTime(1984, 11, 22, 0, 0, 0));
    assertEquals(testBean.getBigDecimal(), BigDecimal.valueOf(0.00000000000000000001));
    assertTrue(testBean.isBoolean1());
    assertFalse(testBean.getBoolean2());
  }

  public static void assertTestBean2Equals(TestBean testBean) {
    assertEquals(testBean.getInt1(), 1);
    assertNull(testBean.getInt2());
    assertEquals(testBean.getLong1(), 1);
    assertNull(testBean.getLong2());
    assertEquals(testBean.getFloat1(), 0.1F);
    assertNull(testBean.getFloat2());
    assertEquals(testBean.getDouble1(), 0.00001D);
    assertNull(testBean.getDouble2());
    assertEquals(testBean.getString(), "Scarlett Johansson");
    assertNull(testBean.getLocalDate());
    assertNull(testBean.getLocalDateTime());
    assertNull(testBean.getBigDecimal());
    assertFalse(testBean.isBoolean1());
    assertNull(testBean.getBoolean2());
  }

  public static void assertTestBeanNull(TestBean testBean) {
    assertEquals(testBean.getInt1(), 0);
    assertNull(testBean.getInt2());
    assertEquals(testBean.getLong1(), 0);
    assertNull(testBean.getLong2());
    assertEquals(testBean.getFloat1(), 0.0F);
    assertNull(testBean.getFloat2());
    assertEquals(testBean.getDouble1(), 0.0D);
    assertNull(testBean.getDouble2());
    assertNull(testBean.getString());
    assertNull(testBean.getLocalDate());
    assertNull(testBean.getLocalDateTime());
    assertNull(testBean.getBigDecimal());
    assertFalse(testBean.isBoolean1());
    assertNull(testBean.getBoolean2());
  }

  public static void assertSheetMetaEquals(SheetMeta s1, SheetMeta s2) {
    assertEquals(s1.getSheetIndex(), s2.getSheetIndex());
    assertEquals(s1.getSheetName(), s2.getSheetName());
    assertEquals(s1.getDataStartRowIndex(), s2.getDataStartRowIndex());

    List<FieldMeta> fm1 = s1.getName2FieldMeta();
    List<FieldMeta> fm2 = s2.getName2FieldMeta();
    assertEquals(fm1.size(), fm2.size());

    for (int i = 0; i < fm1.size(); i++) {
      assertFieldMetaEquals(fm1.get(i), fm2.get(i));
    }
  }

  public static void assertFieldMetaEquals(FieldMeta f1, FieldMeta f2) {
    assertEquals(f1.getColumnIndex(), f2.getColumnIndex());
    assertEquals(f1.getName(), f2.getName());
    List<HeaderMeta> hm1 = f1.getHeaderMetas();
    List<HeaderMeta> hm2 = f2.getHeaderMetas();
    assertEquals(hm1.size(), hm2.size());
    for (int i = 0; i < hm1.size(); i++) {
      assertHeaderMetaEquals(hm1.get(i), hm2.get(i));
    }
  }

  public static void assertHeaderMetaEquals(HeaderMeta h1, HeaderMeta h2) {
    assertEquals(h1.getRowIndex(), h2.getRowIndex());
    assertEquals(h1.getValue(), h2.getValue());
  }
}
