package com.supwisdom.spreadsheet.mapper.model.meta;

import org.apache.commons.collections.CollectionUtils;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by qianjia on 2017/2/12.
 */
public class SheetMetaBeanTest {

  @Test
  public void testAddFieldMetaGood() throws Exception {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("b", 2);

    sheetMetaBean.addFieldMeta(fieldMeta1);
    sheetMetaBean.addFieldMeta(fieldMeta2);

    assertEquals(sheetMetaBean.getFieldMetas("a").size(), 1);
    assertTrue(sheetMetaBean.getFieldMetas("a").contains(fieldMeta1));

    assertEquals(sheetMetaBean.getFieldMetas("b").size(), 1);
    assertTrue(sheetMetaBean.getFieldMetas("b").contains(fieldMeta2));

    assertEquals(sheetMetaBean.getFieldMetas(1), fieldMeta1);
    assertEquals(sheetMetaBean.getFieldMetas(2), fieldMeta2);

  }

  @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*already has FieldMeta for column.*")
  public void testAddFieldMetaBad1() throws Exception {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("b", 1);

    sheetMetaBean.addFieldMeta(fieldMeta1);
    sheetMetaBean.addFieldMeta(fieldMeta2);

  }

  @Test
  public void testAddSameNameFieldMeta() throws Exception {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("a", 2);

    sheetMetaBean.addFieldMeta(fieldMeta2);
    sheetMetaBean.addFieldMeta(fieldMeta1);

    List<FieldMeta> actualFieldMetas = sheetMetaBean.getFieldMetas("a");
    assertEquals(actualFieldMetas.size(), 2);
    assertEquals(actualFieldMetas.get(0), fieldMeta1);
    assertEquals(actualFieldMetas.get(1), fieldMeta2);
  }

  @Test
  public void testGetUniqueFieldMeta() {
    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    sheetMetaBean.addFieldMeta(fieldMeta1);

    FieldMeta actualFieldMeta = sheetMetaBean.getUniqueFieldMeta("a");
    assertEquals(actualFieldMeta, fieldMeta1);
  }

  @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*duplicated FieldMeta for name.*")
  public void testGetUniqueFieldMetaBad() {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("a", 2);

    sheetMetaBean.addFieldMeta(fieldMeta2);
    sheetMetaBean.addFieldMeta(fieldMeta1);

    FieldMeta actualFieldMeta = sheetMetaBean.getUniqueFieldMeta("a");
    assertEquals(actualFieldMeta, fieldMeta1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "fieldMeta is null")
  public void testAddFieldMetaBad3() throws Exception {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    sheetMetaBean.addFieldMeta(null);

  }

  @Test
  public void testRemoveFieldMeta() throws Exception {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);
    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    sheetMetaBean.addFieldMeta(fieldMeta1);

    sheetMetaBean.removeFieldMeta("a");

    assertTrue(CollectionUtils.isEmpty(sheetMetaBean.getFieldMetas("a")));
    assertNull(sheetMetaBean.getFieldMetas(1));

  }

  @Test
  public void testRemoveFieldMeta2() throws Exception {

    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);
    sheetMetaBean.removeFieldMeta("a");

    assertTrue(CollectionUtils.isEmpty(sheetMetaBean.getFieldMetas("a")));
    assertNull(sheetMetaBean.getFieldMetas(1));

  }

}
