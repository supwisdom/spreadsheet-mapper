package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class BooleanToStringConverterTest {

  @Test
  public void testDoConvert() throws Exception {

    BooleanToStringConverter converter = new BooleanToStringConverter().trueString("pass").falseString("failure");

    assertEquals(converter.convertProperty(true), "pass");
    assertEquals(converter.convertProperty(false), "failure");

    assertEquals(converter.convertProperty(Boolean.TRUE), "pass");
    assertEquals(converter.convertProperty(Boolean.FALSE), "failure");

  }

}
