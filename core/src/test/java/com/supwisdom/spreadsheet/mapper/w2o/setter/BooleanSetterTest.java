package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
public class BooleanSetterTest {

  @DataProvider
  public Object[][] testSetValueParam() {

    return new Object[][] {
        new Object[] { "true", Boolean.TRUE },
        new Object[] { "false", Boolean.FALSE },
        new Object[] { null, null },
    };

  }

  @Test(dataProvider = "testSetValueParam")
  public void testSetValue(String cellValue, Boolean expected) throws Exception {

    FieldMetaBean fieldMeta = new FieldMetaBean("boolean2", 1);
    BooleanSetter setter = new BooleanSetter(Collections.singleton("true"), Collections.singleton("false"));

    TestBean testBean = new TestBean();
    setter.setValue(testBean, new CellBean(cellValue), fieldMeta);
    assertEquals(testBean.getBoolean2(), expected);

  }

}
