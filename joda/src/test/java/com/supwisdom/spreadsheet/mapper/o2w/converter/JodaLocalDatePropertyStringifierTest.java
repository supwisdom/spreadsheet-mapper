package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.joda.time.LocalDate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class JodaLocalDatePropertyStringifierTest {

  @DataProvider
  public Object[][] testDoConvertParam() {
    LocalDate localDate = new LocalDate(1984, 11, 22);
    return new Object[][] {
        new Object[] { localDate, "yyyy-MM-dd", "1984-11-22" },
        new Object[] { localDate, "yyyy-MM", "1984-11" },
        new Object[] { localDate, "yyyy", "1984" }
    };
  }

  @Test(dataProvider = "testDoConvertParam")
  public void testDoConvert(LocalDate localDate, String pattern, String expected) throws Exception {

    JodaLocalDatePropertyStringifier converter = new JodaLocalDatePropertyStringifier(pattern);
    assertEquals(converter.convertProperty(localDate), expected);

  }

}
