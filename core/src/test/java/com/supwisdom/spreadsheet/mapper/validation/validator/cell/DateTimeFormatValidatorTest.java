package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class DateTimeFormatValidatorTest {

  @DataProvider
  public Object[][] param() {

    return new Object[][] {
        new Object[] { "yyyy-MM-dd HH:mm:ss", "2012-12-12 12:12:12", true },
        new Object[] { "yyyy-MM-dd HH:mm:ss", "2012-12-12", false },
        new Object[] { "yyyy-MM-dd HH:mm:ss", "2012-12-12 25:12:12", false },
        new Object[] { "yyyy-MM-dd", "2012-12-12 12:12:12", false },
        new Object[] { "yyyy-MM-dd", "2012-12-12", true },
        new Object[] { "yyyy", "2012", true }
    };

  }

  @Test(dataProvider = "param")
  public void testDoValidate(String pattern, String value, boolean expectedResult) throws Exception {

    DateTimeFormatValidator validator = new DateTimeFormatValidator();
    validator.pattern(pattern);

    assertEquals(validator.validate(new CellBean(value), new FieldMetaBean("a", 1)), expectedResult);

  }

}
