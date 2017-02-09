package com.supwisdom.spreadsheet.mapper.w2o.process.setter.buildin;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.joda.time.LocalDateTime;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by hanwen on 2017/1/5.
 */
public class LocalDateTimeSetterTest {

  @Test
  public void testSet() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();

    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    Map<String, Cell> cellMap2 = TestFactory.createErrorCellMap();

    LocalDateTimeSetter<TestBean> setter = new LocalDateTimeSetter<TestBean>().matchField("localDateTime").pattern("yyyy-MM-dd HH:mm:ss");

    TestBean testBean1 = new TestBean();
    setter.set(testBean1, cellMap1.get("localDateTime"), fieldMetaMap.get("localDateTime"));

    TestBean testBean2 = new TestBean();
    setter.set(testBean2, cellMap2.get("localDateTime"), fieldMetaMap.get("localDateTime"));

    assertEquals(testBean1.getLocalDateTime(), new LocalDateTime(1984, 11, 22, 0, 0, 0));
    assertNull(testBean2.getLocalDateTime());
  }

}
