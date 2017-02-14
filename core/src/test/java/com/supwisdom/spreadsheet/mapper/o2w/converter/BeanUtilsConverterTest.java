package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by hanwen on 2017/1/4.
 */
public class BeanUtilsConverterTest {

  @Test
  public void testGetStringValue() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    BeanUtilsConverter<TestBean> extractor = new BeanUtilsConverter<>();

    TestBean testBean1 = TestFactory.createBean1();
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Cell cell11 = cellMap1.get("int1");
    Cell cell12 = cellMap1.get("int2");
    Cell cell13 = cellMap1.get("long1");
    Cell cell14 = cellMap1.get("long2");
    Cell cell15 = cellMap1.get("float1");
    Cell cell16 = cellMap1.get("float2");
    Cell cell17 = cellMap1.get("double1");
    Cell cell18 = cellMap1.get("double2");
    Cell cell19 = cellMap1.get("boolean1");
    Cell cell110 = cellMap1.get("boolean2");
    Cell cell111 = cellMap1.get("bigDecimal");
    Cell cell112 = cellMap1.get("string");

    assertEquals(extractor.getValue(testBean1, cell11, fieldMetaMap.get("int1")), "10000");
    assertEquals(extractor.getValue(testBean1, cell12, fieldMetaMap.get("int2")), "-20000");
    assertEquals(extractor.getValue(testBean1, cell13, fieldMetaMap.get("long1")), "10000000000000");
    assertEquals(extractor.getValue(testBean1, cell14, fieldMetaMap.get("long2")), "20000000000000");
    assertEquals(extractor.getValue(testBean1, cell15, fieldMetaMap.get("float1")), "0.001");
    assertEquals(extractor.getValue(testBean1, cell16, fieldMetaMap.get("float2")), "2.0E-8");
    assertEquals(extractor.getValue(testBean1, cell17, fieldMetaMap.get("double1")), "1.0E-20");
    assertEquals(extractor.getValue(testBean1, cell18, fieldMetaMap.get("double2")), "2.0E-20");
    assertEquals(extractor.getValue(testBean1, cell19, fieldMetaMap.get("boolean1")), "true");
    assertEquals(extractor.getValue(testBean1, cell110, fieldMetaMap.get("boolean2")), "false");

    assertEquals(extractor.getValue(testBean1, cell111, fieldMetaMap.get("bigDecimal")), "1.0E-20");
    assertEquals(extractor.getValue(testBean1, cell112, fieldMetaMap.get("string")), "Scarlett Johansson");

    TestBean testBean2 = TestFactory.createBean2();
    Map<String, Cell> cellMap2 = TestFactory.createCellMap1();
    Cell cell21 = cellMap2.get("int1");
    Cell cell22 = cellMap2.get("int2");
    Cell cell23 = cellMap2.get("long1");
    Cell cell24 = cellMap2.get("long2");
    Cell cell25 = cellMap2.get("float1");
    Cell cell26 = cellMap2.get("float2");
    Cell cell27 = cellMap2.get("double1");
    Cell cell28 = cellMap2.get("double2");
    Cell cell29 = cellMap2.get("boolean1");
    Cell cell210 = cellMap2.get("boolean2");
    Cell cell211 = cellMap2.get("bigDecimal");
    Cell cell212 = cellMap2.get("string");

    assertEquals(extractor.getValue(testBean2, cell21, fieldMetaMap.get("int1")), "1");
    assertNull(extractor.getValue(testBean2, cell22, fieldMetaMap.get("int2")));
    assertEquals(extractor.getValue(testBean2, cell23, fieldMetaMap.get("long1")), "1");
    assertNull(extractor.getValue(testBean2, cell24, fieldMetaMap.get("long2")));
    assertEquals(extractor.getValue(testBean2, cell25, fieldMetaMap.get("float1")), "0.1");
    assertNull(extractor.getValue(testBean2, cell26, fieldMetaMap.get("float2")));
    assertEquals(extractor.getValue(testBean2, cell27, fieldMetaMap.get("double1")), "1.0E-5");
    assertNull(extractor.getValue(testBean2, cell28, fieldMetaMap.get("double2")));
    assertEquals(extractor.getValue(testBean2, cell29, fieldMetaMap.get("boolean1")), "false");
    assertNull(extractor.getValue(testBean2, cell210, fieldMetaMap.get("boolean2")));

    assertNull(extractor.getValue(testBean2, cell211, fieldMetaMap.get("bigDecimal")));
    assertEquals(extractor.getValue(testBean2, cell212, fieldMetaMap.get("string")), "Scarlett Johansson");

    assertNull(extractor.getValue(testBean2, new CellBean("businessKey"), new FieldMetaBean("businessKey", 1)));
  }

}
