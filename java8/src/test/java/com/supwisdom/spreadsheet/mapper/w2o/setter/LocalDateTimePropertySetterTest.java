package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LocalDateTimePropertySetterTest {

  @DataProvider
  public Object[][] testSetPropertyParam() {

    return new Object[][] {
        new Object[] { "1984-11-22 00:00:01", LocalDateTime.of(1984, 11, 22, 0, 0, 1) },
        new Object[] { null, null }
    };

  }

  @Test(dataProvider = "testSetPropertyParam")
  public void testSetProperty(String cellValue, LocalDateTime expected) throws Exception {

    FieldMetaBean fieldMeta = new FieldMetaBean("localDateTime", 1);
    LocalDateTimePropertySetter setter = new LocalDateTimePropertySetter("yyyy-MM-dd HH:mm:ss");

    TestBean testBean = new TestBean();
    setter.setProperty(testBean, new CellBean(cellValue), fieldMeta);
    assertEquals(testBean.getLocalDateTime(), expected);

  }

}
