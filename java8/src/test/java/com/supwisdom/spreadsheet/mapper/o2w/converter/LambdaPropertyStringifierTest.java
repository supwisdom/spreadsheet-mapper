package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaPropertyStringifierTest {

  @Test
  public void testConvert() throws Exception {
    LambdaPropertyStringifier<Boolean> converter = new LambdaPropertyStringifier<>((b) -> b ? "Yes" : "No");
    assertEquals(converter.convertProperty(true), "Yes");
  }

}
