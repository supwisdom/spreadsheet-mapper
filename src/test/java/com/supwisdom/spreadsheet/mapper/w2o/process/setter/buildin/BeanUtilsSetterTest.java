package com.supwisdom.spreadsheet.mapper.w2o.process.setter.buildin;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.math.BigDecimal;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by hanwen on 2017/1/5.
 */
public class BeanUtilsSetterTest {

  @Test
  public void testSet() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();

    BeanUtilsSetter<TestBean> setter = new BeanUtilsSetter<>();


    TestBean testBean = new TestBean();
    Map<String, Cell> cellMap1 = TestFactory.createCellMap1();
    setter.set(testBean, cellMap1.get("int1"), fieldMetaMap.get("int1"));
    setter.set(testBean, cellMap1.get("int2"), fieldMetaMap.get("int2"));
    setter.set(testBean, cellMap1.get("long1"), fieldMetaMap.get("long1"));
    setter.set(testBean, cellMap1.get("long2"), fieldMetaMap.get("long2"));
    setter.set(testBean, cellMap1.get("float1"), fieldMetaMap.get("float1"));
    setter.set(testBean, cellMap1.get("float2"), fieldMetaMap.get("float2"));
    setter.set(testBean, cellMap1.get("double1"), fieldMetaMap.get("double1"));
    setter.set(testBean, cellMap1.get("double2"), fieldMetaMap.get("double2"));
    setter.set(testBean, cellMap1.get("string"), fieldMetaMap.get("string"));
    setter.set(testBean, cellMap1.get("bigDecimal"), fieldMetaMap.get("bigDecimal"));
    setter.set(testBean, cellMap1.get("localDate"), fieldMetaMap.get("localDate"));
    setter.set(testBean, cellMap1.get("localDateTime"), fieldMetaMap.get("localDateTime"));
    setter.set(testBean, cellMap1.get("boolean1"), fieldMetaMap.get("boolean1"));
    setter.set(testBean, cellMap1.get("boolean2"), fieldMetaMap.get("boolean2"));


    assertEquals(testBean.getInt1(), 10000);
    assertEquals(testBean.getInt2(), new Integer(-20000));
    assertEquals(testBean.getLong1(), 10000000000000L);
    assertEquals(testBean.getLong2(), new Long(20000000000000L));
    assertEquals(testBean.getFloat1(), 0.001F);
    assertEquals(testBean.getFloat2(), 0.00000002F);
    assertEquals(testBean.getDouble1(), 0.00000000000000000001D);
    assertEquals(testBean.getDouble2(), 0.00000000000000000002D);
    assertEquals(testBean.getString(), "Scarlett Johansson");
    assertNull(testBean.getLocalDate());
    assertNull(testBean.getLocalDateTime());
    assertEquals(testBean.getBigDecimal(), BigDecimal.valueOf(0.00000000000000000001));
    assertFalse(testBean.isBoolean1());
    assertNull(testBean.getBoolean2());


    TestBean testBean1 = new TestBean();
    Map<String, Cell> cellMap2 = TestFactory.createErrorCellMap();
    setter.set(testBean1, cellMap2.get("int1"), fieldMetaMap.get("int1"));
    setter.set(testBean1, cellMap2.get("int2"), fieldMetaMap.get("int2"));
    setter.set(testBean1, cellMap2.get("long1"), fieldMetaMap.get("long1"));
    setter.set(testBean1, cellMap2.get("long2"), fieldMetaMap.get("long2"));
    setter.set(testBean1, cellMap2.get("float1"), fieldMetaMap.get("float1"));
    setter.set(testBean1, cellMap2.get("float2"), fieldMetaMap.get("float2"));
    setter.set(testBean1, cellMap2.get("double1"), fieldMetaMap.get("double1"));
    setter.set(testBean1, cellMap2.get("double2"), fieldMetaMap.get("double2"));
    setter.set(testBean1, cellMap2.get("string"), fieldMetaMap.get("string"));
    setter.set(testBean1, cellMap2.get("bigDecimal"), fieldMetaMap.get("bigDecimal"));
    setter.set(testBean1, cellMap2.get("localDate"), fieldMetaMap.get("localDate"));
    setter.set(testBean1, cellMap2.get("localDateTime"), fieldMetaMap.get("localDateTime"));
    setter.set(testBean1, cellMap2.get("boolean1"), fieldMetaMap.get("boolean1"));
    setter.set(testBean1, cellMap2.get("boolean2"), fieldMetaMap.get("boolean2"));

    assertEquals(testBean1.getInt1(), 0);
    assertNull(testBean1.getInt2());
    assertEquals(testBean1.getLong1(), 0);
    assertNull(testBean1.getLong2());
    assertEquals(testBean1.getFloat1(), 0.0F);
    assertNull(testBean1.getFloat2());
    assertEquals(testBean1.getDouble1(), 0.0D);
    assertNull(testBean1.getDouble2());
    assertEquals(testBean1.getString(), "Scarlett Johansson");
    assertNull(testBean1.getLocalDate());
    assertNull(testBean1.getLocalDateTime());
    assertEquals(testBean1.getBigDecimal(), BigDecimal.valueOf(0.00000000000000000001).stripTrailingZeros());
    assertFalse(testBean1.isBoolean1());
    assertNull(testBean1.getBoolean2());
  }

}
