package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.BooleanParam;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Collections;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/4.
 */
public class BooleanValidatorTest {

  @Test
  public void testCustomValidate() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    BooleanValidator validator0 = new BooleanValidator(
        Collections.singleton("pass"),
        Collections.singleton("failure")
    );
    validator0.matchField("boolean1");
    BooleanValidator validator1 = new BooleanValidator(
        Collections.singleton("pass"),
        Collections.singleton("failure")
    );
    validator1.matchField("boolean2");

    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    assertTrue(validator0.valid(cellMap1.get("boolean1"), fieldMetaMap.get("boolean1")));
    assertTrue(validator1.valid(cellMap1.get("boolean2"), fieldMetaMap.get("boolean2")));

    Map<String, Cell> cellMap2 = TestFactory.createErrorCellMap();
    assertFalse(validator0.valid(cellMap2.get("boolean1"), fieldMetaMap.get("boolean1")));
    assertTrue(validator1.valid(cellMap2.get("boolean2"), fieldMetaMap.get("boolean2")));
  }

}
