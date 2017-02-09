package com.supwisdom.spreadsheet.mapper.o2w.compose.converter.buildin;

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
public class BooleanExtractorTest {

  @Test
  public void testGetStringValue() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    FieldMeta fieldMeta1 = fieldMetaMap.get("boolean1");
    FieldMeta fieldMeta2 = fieldMetaMap.get("boolean2");

    BooleanConverter<TestBean> extractor1 = new BooleanConverter<TestBean>().trueString("pass").falseString("failure").matchField("boolean1");
    BooleanConverter<TestBean> extractor2 = new BooleanConverter<TestBean>().trueString("pass").falseString("failure").matchField("boolean2");

    TestBean testBean1 = TestFactory.createBean1();
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Cell cell11 = cellMap1.get("boolean1");
    Cell cell12 = cellMap1.get("boolean2");
    String s11 = extractor1.getValue(testBean1, cell11, fieldMeta1);
    assertEquals(s11, "pass");

    String s12 = extractor2.getValue(testBean1, cell12, fieldMeta2);
    assertEquals(s12, "failure");

    TestBean testBean2 = TestFactory.createBean2();
    Map<String, Cell> cellMap2 = TestFactory.createCellMap2();
    Cell cell21 = cellMap2.get("boolean1");
    Cell cell22 = cellMap2.get("boolean2");
    String s21 = extractor1.getValue(testBean2, cell21, fieldMeta1);
    assertEquals(s21, "failure");

    String s22 = extractor2.getValue(testBean2, cell22, fieldMeta2);
    assertNull(s22);
  }

}
