package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
public class DefaultPropertySetterTest {

  @DataProvider
  public Object[][] testSetPropertyParam() {

    return new Object[][] {
        new Object[] { "int1", "1", 1 },
        new Object[] { "int2", "2", 2 },
        new Object[] { "int2", null, null },

        new Object[] { "long1", "1", 1L },
        new Object[] { "long2", "2", 2L },
        new Object[] { "long2", null, null },

        new Object[] { "float1", "1", 1F },
        new Object[] { "float2", "2", 2F },
        new Object[] { "float2", null, null },

        new Object[] { "double1", "1", 1D },
        new Object[] { "double2", "2", 2D },
        new Object[] { "double2", null, null },

        new Object[] { "boolean1", "true", true },
        new Object[] { "boolean2", "false", false },
        new Object[] { "boolean2", null, null },

        new Object[] { "string", "yes or no", "yes or no" },
        new Object[] { "string", null, null },

        new Object[] { "bigDecimal", "12", BigDecimal.valueOf(12) },
        new Object[] { "bigDecimal", null, null },

    };
  }

  @Test(dataProvider = "testSetPropertyParam")
  public void testSetProperty(String field, String cellValue, Object expected) throws Exception {

    DefaultPropertySetter setter = new DefaultPropertySetter();
    TestBean testBean = new TestBean();
    setter.setProperty(testBean, new CellBean(cellValue), new FieldMetaBean(field, 1));

    assertEquals(PropertyUtils.getProperty(testBean, field), expected);
  }

}
