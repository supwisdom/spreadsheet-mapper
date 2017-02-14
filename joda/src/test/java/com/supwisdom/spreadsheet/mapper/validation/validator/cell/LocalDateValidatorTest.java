package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LocalDateValidatorTest {

  @Test
  public void testCustomValidate() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    LocalDateValidator validator = new LocalDateValidator();
    validator.matchField("localDate");
    validator.pattern("yyyy-MM-dd");

    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    assertTrue(validator.validate(cellMap1.get("localDate"), fieldMetaMap.get("localDate")));

    Map<String, Cell> cellMap2 = TestFactory.createErrorCellMap();
    assertFalse(validator.validate(cellMap2.get("localDate"), fieldMetaMap.get("localDate")));
  }

}
