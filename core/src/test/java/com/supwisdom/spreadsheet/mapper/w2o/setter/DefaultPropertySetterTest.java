package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.bean.Bar;
import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.PropertyUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

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

        new Object[] { "bar.stringProperty", null, null },
        new Object[] { "bar.stringProperty", "ABC", "ABC" },

    };
  }

  /**
   * 测试正常情况下的propertySet
   *
   * @param field
   * @param cellValue
   * @param expected
   * @throws Exception
   */
  @Test(dataProvider = "testSetPropertyParam")
  public void testSetProperty(String field, String cellValue, Object expected) throws Exception {

    DefaultPropertySetter setter = new DefaultPropertySetter();
    Foo foo = new Foo();
    foo.setBar(new Bar());
    setter.setProperty(foo, field, cellValue);

    assertEquals(PropertyUtils.getProperty(foo, field), expected);
  }

  @Test(expectedExceptions = Workbook2ObjectComposeException.class)
  public void testNestPropertyNull() {

    DefaultPropertySetter setter = new DefaultPropertySetter();
    Foo foo = new Foo();
    setter.setProperty(foo, "bar.stringProperty", "ABC");

  }

  @Test(expectedExceptions = Workbook2ObjectComposeException.class)
  public void testOnlyGetterProperty() {

    DefaultPropertySetter setter = new DefaultPropertySetter();
    Foo foo = new Foo();
    setter.setProperty(foo, "onlyGetterProperty", "ABC");

    assertEquals(foo.getOnlyGetterProperty(), "ABC");

  }

  @Test
  public void testOnlySetterProperty() {

    DefaultPropertySetter setter = new DefaultPropertySetter();
    Foo foo = new Foo();
    setter.setProperty(foo, "onlySetterProperty", "ABC");

  }

  @Test(expectedExceptions = Workbook2ObjectComposeException.class)
  public void testNotExistProperty() {

    DefaultPropertySetter setter = new DefaultPropertySetter();
    Foo foo = new Foo();
    setter.setProperty(foo, "notExistProperty", "ABC");

  }


}
