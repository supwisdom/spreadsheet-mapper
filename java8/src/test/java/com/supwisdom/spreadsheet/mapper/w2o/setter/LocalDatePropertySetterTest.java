package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LocalDatePropertySetterTest {

  @DataProvider
  public Object[][] testSetPropertyParam() {

    return new Object[][] {
        new Object[] { "2012-12-12", LocalDate.of(2012, 12, 12) },
        new Object[] { null, null }
    };

  }

  @Test(dataProvider = "testSetPropertyParam")
  public void testSetProperty(String cellValue, LocalDate expected) throws Exception {

    FieldMetaBean fieldMeta = new FieldMetaBean("localDate", 1);
    LocalDatePropertySetter setter = new LocalDatePropertySetter("yyyy-MM-dd");

    TestBean testBean = new TestBean();
    setter.setProperty(testBean, new CellBean(cellValue), fieldMeta);
    assertEquals(testBean.getLocalDate(), expected);

  }

}
