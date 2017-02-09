package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/4.
 */
public class UniqueValidatorTest {

  @Test
  public void testCustomValidate() throws Exception {


    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();

    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Map<String, Cell> cellMap2 = TestFactory.createErrorCellMap();

    UniqueValidator validator = new UniqueValidator();
    validator.matchField("string");

    assertTrue(validator.valid(cellMap1.get("string"), fieldMetaMap.get("string")));
    assertFalse(validator.valid(cellMap2.get("string"), fieldMetaMap.get("string")));

    assertTrue(validator.valid(cellMap1.get("int1"), fieldMetaMap.get("int1")));
    assertTrue(validator.valid(cellMap2.get("int1"), fieldMetaMap.get("int1")));

    assertTrue(validator.valid(cellMap1.get("int2"), fieldMetaMap.get("int2")));
    assertTrue(validator.valid(cellMap2.get("int2"), fieldMetaMap.get("int2")));

    assertTrue(validator.valid(cellMap1.get("bigDecimal"), fieldMetaMap.get("bigDecimal")));
    assertTrue(validator.valid(cellMap2.get("bigDecimal"), fieldMetaMap.get("bigDecimal")));

    assertTrue(validator.valid(cellMap1.get("localDate"), fieldMetaMap.get("localDate")));
    assertTrue(validator.valid(cellMap2.get("localDate"), fieldMetaMap.get("localDate")));
  }

}
