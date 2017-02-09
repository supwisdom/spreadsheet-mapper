package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by hanwen on 2017/1/4.
 */
public class PlainNumberExtractorTest {

  @Test
  public void testGetStringValue() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    FieldMeta fieldMeta1 = fieldMetaMap.get("double1");
    FieldMeta fieldMeta2 = fieldMetaMap.get("double2");
    FieldMeta fieldMeta3 = fieldMetaMap.get("long1");
    FieldMeta fieldMeta4 = fieldMetaMap.get("long2");
    FieldMeta fieldMeta5 = fieldMetaMap.get("int1");
    FieldMeta fieldMeta6 = fieldMetaMap.get("int2");
    FieldMeta fieldMeta7 = fieldMetaMap.get("float1");
    FieldMeta fieldMeta8 = fieldMetaMap.get("float2");
    FieldMeta fieldMeta9 = fieldMetaMap.get("bigDecimal");

    PlainNumberConverter<TestBean> extractor1 = new PlainNumberConverter<TestBean>().matchField("double1");
    PlainNumberConverter<TestBean> extractor2 = new PlainNumberConverter<TestBean>().matchField("double2");
    PlainNumberConverter<TestBean> extractor3 = new PlainNumberConverter<TestBean>().matchField("long1");
    PlainNumberConverter<TestBean> extractor4 = new PlainNumberConverter<TestBean>().matchField("long2");
    PlainNumberConverter<TestBean> extractor5 = new PlainNumberConverter<TestBean>().matchField("int1");
    PlainNumberConverter<TestBean> extractor6 = new PlainNumberConverter<TestBean>().matchField("int2");
    PlainNumberConverter<TestBean> extractor7 = new PlainNumberConverter<TestBean>().matchField("float1");
    PlainNumberConverter<TestBean> extractor8 = new PlainNumberConverter<TestBean>().matchField("float2");
    PlainNumberConverter<TestBean> extractor9 = new PlainNumberConverter<TestBean>().matchField("float2");

    TestBean testBean1 = TestFactory.createBean1();
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Cell cell11 = cellMap1.get("double1");
    Cell cell12 = cellMap1.get("double2");
    Cell cell13 = cellMap1.get("long1");
    Cell cell14 = cellMap1.get("long2");
    Cell cell15 = cellMap1.get("int1");
    Cell cell16 = cellMap1.get("int2");
    Cell cell17 = cellMap1.get("float1");
    Cell cell18 = cellMap1.get("float2");
    Cell cell19 = cellMap1.get("bigDecimal");

    assertEquals(extractor1.getValue(testBean1, cell11, fieldMeta1), "0.00000000000000000001");
    assertEquals(extractor2.getValue(testBean1, cell12, fieldMeta2), "0.00000000000000000002");
    assertEquals(extractor3.getValue(testBean1, cell13, fieldMeta3), "10000000000000");
    assertEquals(extractor4.getValue(testBean1, cell14, fieldMeta4), "20000000000000");
    assertEquals(extractor5.getValue(testBean1, cell15, fieldMeta5), "10000");
    assertEquals(extractor6.getValue(testBean1, cell16, fieldMeta6), "-20000");
    assertEquals(extractor7.getValue(testBean1, cell17, fieldMeta7), "0.001");
    assertEquals(extractor8.getValue(testBean1, cell18, fieldMeta8), "0.00000002");
    assertEquals(extractor9.getValue(testBean1, cell19, fieldMeta9), "0.00000000000000000001");

    TestBean testBean2 = TestFactory.createBean2();
    Map<String, Cell> cellMap2 = TestFactory.createCellMap2();
    Cell cell21 = cellMap2.get("double1");
    Cell cell22 = cellMap2.get("double2");
    Cell cell23 = cellMap2.get("long1");
    Cell cell24 = cellMap2.get("long2");
    Cell cell25 = cellMap2.get("int1");
    Cell cell26 = cellMap2.get("int2");
    Cell cell27 = cellMap2.get("float1");
    Cell cell28 = cellMap2.get("float2");
    Cell cell29 = cellMap2.get("bigDecimal");

    assertEquals(extractor1.getValue(testBean2, cell21, fieldMeta1), "0.00001");
    assertNull(extractor2.getValue(testBean2, cell22, fieldMeta2));
    assertEquals(extractor3.getValue(testBean2, cell23, fieldMeta3), "1");
    assertNull(extractor4.getValue(testBean2, cell24, fieldMeta4));
    assertEquals(extractor5.getValue(testBean2, cell25, fieldMeta5), "1");
    assertNull(extractor6.getValue(testBean2, cell26, fieldMeta6));
    assertEquals(extractor7.getValue(testBean2, cell27, fieldMeta7), "0.1");
    assertNull(extractor8.getValue(testBean2, cell28, fieldMeta8));
    assertNull(extractor9.getValue(testBean2, cell29, fieldMeta9));

  }

}
