package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaPropertyStringifier2Test {

  @Test
  public void testConvert() throws Exception {
    LambdaPropertyStringifier2<Object> converter = new LambdaPropertyStringifier2<>((b, fieldMeta) -> "Yes");
    assertEquals(converter.getPropertyString(null, new FieldMetaBean("a", 1)), "Yes");
  }

}
