package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaToStringConverterTest {

  @Test
  public void testConvert() throws Exception {
    LambdaToStringConverter<Boolean> converter = new LambdaToStringConverter<>((b) -> b ? "Yes" : "No");
    assertEquals(converter.convertProperty(true), "Yes");
  }

}
