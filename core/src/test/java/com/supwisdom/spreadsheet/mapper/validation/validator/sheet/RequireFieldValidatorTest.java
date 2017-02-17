package com.supwisdom.spreadsheet.mapper.validation.validator.sheet;

import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class RequireFieldValidatorTest {

  @DataProvider
  public Object[][] testValidateParam() {

    return new Object[][] {
        new Object[] { new String[0], new String[0], true },
        new Object[] { new String[0], new String[] { "a" }, true },
        new Object[] { new String[] { "a" }, new String[] { "a" }, true },
        new Object[] { new String[] { "a" }, new String[] { "a", "b" }, true },
        new Object[] { new String[] { "b", "a" }, new String[] { "a", "b" }, true },

        new Object[] { new String[] { "b" }, new String[] { "a", "b" }, true },
        new Object[] { new String[] { "b" }, new String[] { "a" }, false },
        new Object[] { new String[] { "b" }, new String[0], false },

    };
  }

  @Test(dataProvider = "testValidateParam")
  public void testValidate(String[] requireFields, String[] fieldNames, boolean expected) {

    RequireFieldValidator validator = new RequireFieldValidator(requireFields);

    SheetMetaBean sheetMeta = new SheetMetaBean("test", 2);
    for (int i = 0; i < fieldNames.length; i++) {
      sheetMeta.addFieldMeta(new FieldMetaBean(fieldNames[i], i + 1));
    }
    boolean valid = validator.validate(new SheetBean("test"), sheetMeta);
    assertEquals(valid, expected);

  }

}
