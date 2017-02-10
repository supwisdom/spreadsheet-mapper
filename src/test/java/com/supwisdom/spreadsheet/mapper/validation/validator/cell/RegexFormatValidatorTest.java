package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

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
public class RegexFormatValidatorTest {

  @Test
  public void testCustomValidate() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();

    RegexFormatValidator validator0 = new RegexFormatValidator();
    validator0.regex("^[1-9]\\d*$");
    validator0.matchField("int1");
    RegexFormatValidator validator1 = new RegexFormatValidator();
    validator1.regex("^[1-9]\\d*$");
    validator1.matchField("int2");
    RegexFormatValidator validator2 = new RegexFormatValidator();
    validator2.regex("^[1-9]\\d*$");
    validator2.matchField("long1");
    RegexFormatValidator validator3 = new RegexFormatValidator();
    validator3.regex("^[1-9]\\d*$");
    validator3.matchField("double1");
    RegexFormatValidator validator4 = new RegexFormatValidator();
    validator4.regex("^[1-9]\\d*$");
    validator4.matchField("string");
    RegexFormatValidator validator5 = new RegexFormatValidator();
    validator5.regex("^[1-9]\\d*$");
    validator5.matchField("localDate");


    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();

    assertTrue(validator0.valid(cellMap1.get("int1"), fieldMetaMap.get("int1")));
    assertFalse(validator1.valid(cellMap1.get("int2"), fieldMetaMap.get("int2")));
    assertTrue(validator2.valid(cellMap1.get("long1"), fieldMetaMap.get("long1")));
    assertFalse(validator3.valid(cellMap1.get("double1"), fieldMetaMap.get("double1")));
    assertFalse(validator4.valid(cellMap1.get("string"), fieldMetaMap.get("string")));
    assertFalse(validator5.valid(cellMap1.get("localDate"), fieldMetaMap.get("localDate")));
  }

}
