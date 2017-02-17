package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class NumberScaleRangeValidatorTest {

  @DataProvider
  public Object[][] testDoValidateParam() {

    return new Object[][] {
        new Object[] { 1, 2, "1.1", true },
        new Object[] { 1, 2, "1.11", true },
        new Object[] { 0, 2, "1", true },
        new Object[] { 1, 2, " 1", false },
        new Object[] { 1, 2, "1", false },
        new Object[] { 1, 2, "1.111 ", false },
    };

  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(int gte, int lte, String cellValue, boolean expected) throws Exception {
    NumberScaleRangeValidator validator = new NumberScaleRangeValidator(gte, lte);
    boolean valid = validator.doValidate(new CellBean(cellValue), new FieldMetaBean("sth", 1));
    assertEquals(valid, expected);

  }

}
