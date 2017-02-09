package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/4.
 */
public class MultiUniqueValidatorTest {

  @Test
  public void testCustomValidate() throws Exception {

    MultiUniqueValidator validator1 = new MultiUniqueValidator();
    validator1.matchFields("int1", "int2");
    validator1.group("multi.unique");

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    List<FieldMeta> fieldMetas = Arrays.asList(fieldMetaMap.get("int1"), fieldMetaMap.get("int2"));

    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Map<String, Cell> cellMap2 = TestFactory.createCellMap2();
    List<Cell> cells1 = Arrays.asList(cellMap1.get("int1"), cellMap1.get("int2"));
    List<Cell> cells2 = Arrays.asList(cellMap2.get("int1"), cellMap1.get("int2"));

    assertTrue(validator1.valid(cells1, fieldMetas));
    assertTrue(validator1.valid(cells2, fieldMetas));

    MultiUniqueValidator validator2 = new MultiUniqueValidator();
    validator2.matchFields("string");
    validator2.group("multi.unique");

    assertTrue(validator2.valid(Collections.singletonList(cellMap2.get("string")), fieldMetas));
    assertFalse(validator2.valid(Collections.singletonList(cellMap2.get("string")), fieldMetas));

  }

}
