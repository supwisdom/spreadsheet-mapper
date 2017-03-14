package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
public class BooleanPropertySetterTest {

  @DataProvider
  public Object[][] testSetPropertyParam() {

    return new Object[][] {
        new Object[] { "true", Boolean.TRUE },
        new Object[] { "false", Boolean.FALSE },
        new Object[] { null, null },
    };

  }

  @Test(dataProvider = "testSetPropertyParam")
  public void testSetProperty(String cellValue, Boolean expected) throws Exception {

    FieldMetaBean fieldMeta = new FieldMetaBean("boolean2", 1);
    BooleanPropertySetter setter = new BooleanPropertySetter(Collections.singleton("true"), Collections.singleton("false"));

    Foo foo = new Foo();
    setter.setProperty(foo, new CellBean(cellValue), fieldMeta);
    assertEquals(foo.getBoolean2(), expected);

  }

}
