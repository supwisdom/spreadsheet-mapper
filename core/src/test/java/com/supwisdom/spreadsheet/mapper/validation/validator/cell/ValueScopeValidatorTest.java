package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/17.
 */
public class ValueScopeValidatorTest {

  @DataProvider
  public Object[][] testDoValidateParam() {
    return new Object[][] {
        new Object[] { new String[] { "1" }, "1", true },
        new Object[] { new String[] { "1", "2" }, "1", true },
        new Object[] { new String[] { "1", "2" }, "2", true },
        new Object[] { new String[] { "1", "2" }, "3", false }
    };
  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String[] restictValues, String cellValue, boolean expected) throws Exception {

    ValueScopeValidator validator = new ValueScopeValidator(restictValues);
    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
