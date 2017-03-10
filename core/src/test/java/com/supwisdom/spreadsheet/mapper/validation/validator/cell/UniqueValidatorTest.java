package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class UniqueValidatorTest {

  private UniqueValidator validator = new UniqueValidator();

  @DataProvider
  public Object[][] testDoValidateParam() {

    return new Object[][] {
        new Object[] { "a", true },
        new Object[] { "b", true },
        new Object[] { "c", true },
        new Object[] { "a", false },
        new Object[] { "a ", true },
        new Object[] { " a", true },
        new Object[] { " a ", true },
        new Object[] { "c", false },
    };
  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String cellValue, boolean expected) throws Exception {

    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
