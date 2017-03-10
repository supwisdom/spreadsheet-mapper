package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class RegexFormatValidatorTest {

  @DataProvider
  public Object[][] testDoValidateParam() {

    String regexp = "^[1-9]\\d*$";

    return new Object[][] {
        new Object[] { regexp, "1", true },
        new Object[] { regexp, " 1", false },
        new Object[] { regexp, "1.1 ", false },
        new Object[] { regexp, "1d", false },
        new Object[] { regexp, "1L", false },
        new Object[] { regexp, "-1", false }
    };
  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String regexp, String cellValue, boolean expected) throws Exception {

    RegexFormatValidator validator = new RegexFormatValidator(regexp);
    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
