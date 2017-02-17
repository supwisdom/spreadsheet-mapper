package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class RequireValidatorTest {

  @DataProvider
  public Object[][] testDoValidateParam() {
    return new Object[][] {
        new Object[] { "1", true },
        new Object[] { " 1", true },
        new Object[] { "1 ", true },
        new Object[] { " 1 ", true },
        new Object[] { "", false },
        new Object[] { "  ", false },
        new Object[] { "\n", false },
        new Object[] { "\t", false },
        new Object[] { null, false }
    };
  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String cellValue, boolean expected) throws Exception {

    RequireValidator validator = new RequireValidator();
    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
