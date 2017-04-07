package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/4/7.
 */
public class NoopPropertySetterTest {

  @DataProvider
  public Object[][] testSetPropertyParam() {

    return new Object[][] {
        new Object[] { "true" },
        new Object[] { "false" },
        new Object[] { null },
    };

  }

  @Test(dataProvider = "testSetPropertyParam")
  public void testSetProperty(String cellValue) throws Exception {

    FieldMetaBean fieldMeta = new FieldMetaBean("boolean2", 1);
    NoopPropertySetter setter = new NoopPropertySetter("boolean2");

    Foo foo = new Foo();
    setter.setProperty(foo, new CellBean(cellValue), fieldMeta);
    assertEquals(foo.getBoolean2(), null);

  }

}
