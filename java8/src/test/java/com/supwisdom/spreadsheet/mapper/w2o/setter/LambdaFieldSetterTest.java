package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaFieldSetterTest {
  @Test
  public void testLambda() throws Exception {

    LambdaPropertySetter setter = new LambdaPropertySetter(s -> 1L);

    TestBean testBean = new TestBean();
    setter.setProperty(testBean, new CellBean("abc"), new FieldMetaBean("long1", 1));

    assertEquals(testBean.getLong1(), new Long(1L));
  }

}

