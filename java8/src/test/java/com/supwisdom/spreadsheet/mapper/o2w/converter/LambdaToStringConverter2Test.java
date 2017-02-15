package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaToStringConverter2Test {

  @Test
  public void testConvert() throws Exception {
    LambdaToStringConverter2<Object> converter = new LambdaToStringConverter2<>((b, fieldMeta) -> "Yes");
    assertEquals(converter.getString(null, new FieldMetaBean("a", 1)), "Yes");
  }

}
