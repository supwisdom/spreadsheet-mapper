package com.supwisdom.spreadsheet.mapper.bean;

import org.apache.commons.beanutils.PropertyUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by qianjia on 2017/3/14.
 */
public class BeanHelperBeanTest {

  /**
   * 测试简单的获取属性
   *
   * @throws Exception
   */
  @Test
  public void testGetProperty() throws Exception {

    BeanHelper beanHelper = new BeanHelperBean();

    Foo foo = new Foo();
    foo.setIntProperty(100);

    Object intProperty = beanHelper.getProperty(foo, "intProperty");
    assertEquals(intProperty, new Integer(100));

  }

  /**
   * 测试获取只有getter方法的属性
   *
   * @throws Exception
   */
  @Test
  public void testGetOnlyGetterProperty() throws Exception {

    BeanHelper beanHelper = new BeanHelperBean();

    Foo foo = new Foo();

    Object intProperty = beanHelper.getProperty(foo, "onlyGetterProperty");
    assertEquals(intProperty, "onlyGetterProperty");

  }

  /**
   * 测试获取只有setter方法的属性
   *
   * @throws Exception
   */
  @Test(expectedExceptions = BeanPropertyReadException.class)
  public void testGetOnlySetterProperty() throws Exception {

    BeanHelper beanHelper = new BeanHelperBean();

    Foo foo = new Foo();

    Object intProperty = beanHelper.getProperty(foo, "onlySetterProperty");
    assertEquals(intProperty, "onlySetterProperty");

  }

  /**
   * 测试获取 a.b.c 的时候，b为null的情况
   *
   * @throws Exception
   */
  @Test
  public void testGetNulNestProperty() throws Exception {

    BeanHelper beanHelper = new BeanHelperBean();

    Foo foo = new Foo();

    Object property = beanHelper.getProperty(foo, "bar.stringProperty");
    assertNull(property);

  }

  /**
   * 测试获取 a.b.c
   */
  @Test
  public void testGetNestProperty() throws Exception {

    BeanHelper beanHelper = new BeanHelperBean();

    Foo foo = new Foo();
    Bar bar = new Bar();
    bar.setStringProperty("ABCDEFG");
    foo.setBar(bar);

    Object property = beanHelper.getProperty(foo, "bar.stringProperty");
    assertEquals(property, "ABCDEFG");

  }

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

    BeanHelper beanHelper = new BeanHelperBean();
    Foo foo = new Foo();
    foo.setBar(new Bar());
    beanHelper.setProperty(foo, field, cellValue);

    assertEquals(PropertyUtils.getProperty(foo, field), expected);
  }

  /**
   * 测试设置嵌套属性时，其中中间节点的属性是null时的情况
   *
   * @throws BeanPropertyWriteException
   */
  @Test
  public void testSetNestPropertyNull() throws BeanPropertyWriteException {

    BeanHelper beanHelper = new BeanHelperBean();
    Foo foo = new Foo();
    beanHelper.setProperty(foo, "bar.stringProperty", "ABC");

    assertEquals(foo.getBar().getStringProperty(), "ABC");

  }

  /**
   * 测试设置没有setter的属性。
   *
   * @throws BeanPropertyWriteException
   */
  @Test(expectedExceptions = BeanPropertyWriteException.class)
  public void testOnlyGetterProperty() throws BeanPropertyWriteException {

    BeanHelper beanHelper = new BeanHelperBean();
    Foo foo = new Foo();
    beanHelper.setProperty(foo, "onlyGetterProperty", "ABC");

    assertEquals(foo.getOnlyGetterProperty(), "ABC");

  }

  /**
   * 测试设置只有setter的属性
   *
   * @throws BeanPropertyWriteException
   */
  @Test
  public void testOnlySetterProperty() throws BeanPropertyWriteException {

    BeanHelper beanHelper = new BeanHelperBean();
    Foo foo = new Foo();
    beanHelper.setProperty(foo, "onlySetterProperty", "ABC");

  }

  /**
   * 测试设置不存在的属性
   *
   * @throws BeanPropertyWriteException
   */
  @Test(expectedExceptions = BeanPropertyWriteException.class)
  public void testNotExistProperty() throws BeanPropertyWriteException {

    BeanHelper beanHelper = new BeanHelperBean();
    Foo foo = new Foo();
    beanHelper.setProperty(foo, "notExistProperty", "ABC");

  }

  /**
   * 测试嵌套属性没有默认构造函数
   */
  @Test(expectedExceptions = BeanPropertyWriteException.class)
  public void testNoDefaultCtorNestedProperty() throws BeanPropertyWriteException {

    BeanHelper beanHelper = new BeanHelperBean();
    Foo foo = new Foo();
    beanHelper.setProperty(foo, "loo.name", "ABC");

  }

}
