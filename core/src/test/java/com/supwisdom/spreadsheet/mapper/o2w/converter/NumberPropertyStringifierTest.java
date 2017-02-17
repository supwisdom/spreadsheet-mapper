package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class NumberPropertyStringifierTest {

  @Test
  public void testDoConvert() throws Exception {

    NumberPropertyStringifier converter = new NumberPropertyStringifier();

    assertEquals(converter.convertProperty(0.00000000000000000001D), "0.00000000000000000001");
    assertEquals(converter.convertProperty(new Double(0.00000000000000000002D)), "0.00000000000000000002");
    assertEquals(converter.convertProperty(10000000000000L), "10000000000000");
    assertEquals(converter.convertProperty(new Long(20000000000000L)), "20000000000000");
    assertEquals(converter.convertProperty(10000), "10000");
    assertEquals(converter.convertProperty(new Integer(-20000)), "-20000");
    assertEquals(converter.convertProperty(0.001F), "0.001");
    assertEquals(converter.convertProperty(new Float(0.00000002F)), "0.00000002");
    assertEquals(converter.convertProperty(BigDecimal.valueOf(0.00000000000000000001)), "0.00000000000000000001");

  }

}
