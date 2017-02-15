package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaCellValidatorTest {

  @Test
  public void testValidate() throws Exception {

    LambdaCellValidator validator = new LambdaCellValidator((cell, fieldMeta) -> false);
    assertFalse(validator.validate(new CellBean("sth"), new FieldMetaBean("a", 1)));

    LambdaCellValidator validator2 = new LambdaCellValidator((cell, fieldMeta) -> true);
    assertTrue(validator2.validate(new CellBean("sth"), new FieldMetaBean("a", 1)));

  }

}
