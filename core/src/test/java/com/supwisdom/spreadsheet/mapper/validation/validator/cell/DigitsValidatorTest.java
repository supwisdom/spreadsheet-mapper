package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/12.
 */
public class DigitsValidatorTest {

  @DataProvider
  public Object[][] testDoValidateParam() {
    return new Object[][] {
        new Object[] { "1", true },
        new Object[] { "1.1", false },
        new Object[] { " 1", false },
        new Object[] { "1 ", false },
        new Object[] { " 1 ", false },
        new Object[] { "1a", false }
    };
  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String cellValue, boolean expected) throws Exception {

    DigitsValidator validator = new DigitsValidator();
    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
