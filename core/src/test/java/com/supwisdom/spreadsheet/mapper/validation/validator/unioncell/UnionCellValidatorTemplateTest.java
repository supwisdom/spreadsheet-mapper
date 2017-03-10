package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by qianjia on 2017/2/17.
 */
public class UnionCellValidatorTemplateTest {
  @Test
  public void testValidate() throws Exception {

    UnionCellValidatorTemplate validator = new UnionCellValidatorTemplate() {
      @Override
      protected boolean doValidate(List list, List list2) {
        return false;
      }
    };

    assertTrue(validator.validate(Collections.singletonList(new CellBean(null)), Collections.singletonList(new FieldMetaBean("sth", 1))));
    assertFalse(validator.validate(Collections.singletonList(new CellBean("a")), Collections.singletonList(new FieldMetaBean("sth", 1))));

  }

}
