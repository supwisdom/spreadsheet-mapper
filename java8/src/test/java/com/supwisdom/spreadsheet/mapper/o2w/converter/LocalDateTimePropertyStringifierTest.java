package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LocalDateTimePropertyStringifierTest {

  @DataProvider
  public Object[][] testDoConvertParam() {
    LocalDateTime localDateTime = LocalDateTime.of(1984, 11, 22, 0, 0, 0);
    return new Object[][] {
        new Object[] { localDateTime, "yyyy-MM-dd HH:mm:ss", "1984-11-22 00:00:00" },
        new Object[] { localDateTime, "yyyy-MM-dd", "1984-11-22" },
        new Object[] { localDateTime, "yyyy", "1984" }
    };
  }

  @Test(dataProvider = "testDoConvertParam")
  public void testDoConvert(LocalDateTime localDateTime, String pattern, String expected) throws Exception {

    LocalDateTimePropertyStringifier converter = new LocalDateTimePropertyStringifier(pattern);
    assertEquals(converter.convertProperty(localDateTime), expected);

  }

}
