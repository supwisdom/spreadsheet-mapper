package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.joda.time.LocalDate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class JodaLocalDateSetterTest {

  @DataProvider
  public Object[][] testSetValueParam() {

    return new Object[][] {
        new Object[] { "2012-12-12", new LocalDate(2012, 12, 12) },
        new Object[] { null, null }
    };

  }

  @Test(dataProvider = "testSetValueParam")
  public void testSetValue(String cellValue, LocalDate expected) throws Exception {

    FieldMetaBean fieldMeta = new FieldMetaBean("localDate", 1);
    JodaLocalDateSetter setter = new JodaLocalDateSetter("yyyy-MM-dd");

    TestBean testBean = new TestBean();
    setter.setValue(testBean, new CellBean(cellValue), fieldMeta);
    assertEquals(testBean.getLocalDate(), expected);

  }

}
