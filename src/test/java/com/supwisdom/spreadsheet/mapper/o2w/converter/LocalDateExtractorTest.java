package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by hanwen on 2017/1/4.
 */
public class LocalDateExtractorTest {

  @Test
  public void testGetStringValue() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    FieldMeta fieldMeta1 = fieldMetaMap.get("localDate");

    LocalDateConverter<TestBean> extractor1 = new LocalDateConverter<TestBean>().matchField("localDate").pattern("yyyy-MM-dd");
    LocalDateConverter<TestBean> extractor2 = new LocalDateConverter<TestBean>().matchField("localDate").pattern("yyyy-MM");
    LocalDateConverter<TestBean> extractor3 = new LocalDateConverter<TestBean>().matchField("localDate").pattern("yyyy");

    TestBean testBean1 = TestFactory.createBean1();
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Cell cell11 = cellMap1.get("localDate");
    String s11 = extractor1.getValue(testBean1, cell11, fieldMeta1);
    assertEquals(s11, "1984-11-22");

    String s12 = extractor2.getValue(testBean1, cell11, fieldMeta1);
    assertEquals(s12, "1984-11");

    TestBean testBean2 = TestFactory.createBean2();
    Map<String, Cell> cellMap2 = TestFactory.createCellMap2();
    Cell cell21 = cellMap2.get("localDate");
    String s21 = extractor3.getValue(testBean2, cell21, fieldMeta1);
    assertNull(s21);
  }

}
