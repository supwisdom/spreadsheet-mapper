package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class NumberValidatorTest {

  @DataProvider
  public Object[][] testDoValidateParam() {

    return new Object[][] {
        new Object[] { "1", true },
        new Object[] { " 1", false },
        new Object[] { "1.1", true },
        new Object[] { "0", true },
        new Object[] { "01", true },
        new Object[] { "0.1", true },
        new Object[] { "00", true },
        new Object[] { "1f", false },
        new Object[] { "1F", false },
        new Object[] { "1d", false },
        new Object[] { "1D", false },
        new Object[] { "1l", false },
        new Object[] { "1L", false },
        new Object[] { "1.1L", false },
        new Object[] { "a", false }
    };

  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String cellValue, boolean expected) throws Exception {

    NumberValidator validator = new NumberValidator();
    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
