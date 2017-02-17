package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by qianjia on 2017/2/17.
 */
public class CellValidatorTemplateTest {

  @Test
  public void testValidate() throws Exception {

    CellValidatorTemplate validator = new CellValidatorTemplate() {
      @Override
      protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
        return false;
      }
    };

    assertTrue(validator.validate(new CellBean(null), new FieldMetaBean("sth", 1)));
    assertFalse(validator.validate(new CellBean("a"), new FieldMetaBean("sth", 1)));
  }

}
