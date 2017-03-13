package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.bean.Bar;
import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.o2w.Object2WorkbookComposeException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by qianjia on 2017/3/13.
 */
public class PropertyStringifierTemplateTest {

  PropertyStringifierTemplate propertyStringifierTemplate = new PropertyStringifierTemplate() {
    @Override
    protected String convertProperty(Object property) {
      return null;
    }
  };

  /**
   * 测试a.b这样的情况
   *
   * @throws Exception
   */
  @Test
  public void testGetProperty() throws Exception {

    Foo foo = new Foo();
    foo.setIntProperty(100);

    Object intProperty = propertyStringifierTemplate.getProperty(foo, "intProperty");
    assertEquals(intProperty, new Integer(100));

  }

  @Test
  public void testGetOnlyGetterProperty() throws Exception {

    Foo foo = new Foo();

    Object intProperty = propertyStringifierTemplate.getProperty(foo, "onlyGetterProperty");
    assertEquals(intProperty, "onlyGetterProperty");

  }

  @Test(expectedExceptions = Object2WorkbookComposeException.class)
  public void testGetOnlySetterProperty() throws Exception {

    Foo foo = new Foo();

    Object intProperty = propertyStringifierTemplate.getProperty(foo, "onlySetterProperty");
    assertEquals(intProperty, "onlySetterProperty");

  }

  /**
   * 测试获取 a.b.c 的时候，b为null的情况
   *
   * @throws Exception
   */
  @Test
  public void testGetNulNestProperty() throws Exception {

    Foo foo = new Foo();

    Object property = propertyStringifierTemplate.getProperty(foo, "bar.stringProperty");
    assertNull(property);

  }

  /**
   * 测试获取 a.b.c
   */
  @Test
  public void testGetNestProperty() {

    Foo foo = new Foo();
    Bar bar = new Bar();
    bar.setStringProperty("ABCDEFG");
    foo.setBar(bar);

    Object property = propertyStringifierTemplate.getProperty(foo, "bar.stringProperty");
    assertEquals(property, "ABCDEFG");

  }

}
