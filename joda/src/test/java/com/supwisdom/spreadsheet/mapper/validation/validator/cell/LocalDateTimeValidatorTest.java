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
public class LocalDateTimeValidatorTest {

  @Test
  public void testCustomValidate() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    LocalDateTimeValidator validator = new LocalDateTimeValidator();
    validator.matchField("localDateTime");
    validator.pattern("yyyy-MM-dd HH:mm:ss");
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    assertTrue(validator.validate(cellMap1.get("localDateTime"), fieldMetaMap.get("localDateTime")));

    Map<String, Cell> cellMap2 = TestFactory.createErrorCellMap();
    assertFalse(validator.validate(cellMap2.get("localDateTime"), fieldMetaMap.get("localDateTime")));
  }


}
