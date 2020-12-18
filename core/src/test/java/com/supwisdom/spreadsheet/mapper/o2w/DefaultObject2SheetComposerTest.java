package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.o2w.converter.NumberPropertyStringifier;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "defaultObject2SheetComposerTest")
public class DefaultObject2SheetComposerTest {

  /**
   * @return
   */
  @DataProvider
  public Object[][] sheetMetaBeanParam() {
    return new Object[][]{
        new Object[]{1},
        new Object[]{2},
        new Object[]{3},
    };
  }

  /**
   * 测试没有FieldMeta，也没有Data
   * 预期结果：
   * 1) 存在 dataStartRowIndex - 1 数量的行
   * 2) 每行的cell数量为0
   *
   * @throws Exception
   */
  @Test(dataProvider = "sheetMetaBeanParam")
  public void testComposeNothing(int dataStartRowIndex) throws Exception {

    int expectedRows = dataStartRowIndex - 1;

    SheetMeta sheetMeta = new SheetMetaBean("sheet", dataStartRowIndex);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    Sheet sheet = sheetComposer.compose(Collections.emptyList(), sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), expectedRows);
    for (int i = 0; i < expectedRows - 1; i++) {
      Row row = sheet.getRows().get(i);
      assertEquals(row.getIndex(), i + 1);
      assertEquals(row.getCells().size(), 0);
    }

  }

  /**
   * 测试存在FieldMeta，没有HeadMeta，没有Data
   * 预期结果：
   * 1) 存在 dataStartRowIndex - 1 数量的行
   * 2) 每行的cell数量为FieldMeta的数量
   */
  @Test(dataProvider = "sheetMetaBeanParam")
  public void testComposeEmptyFieldMeta(int dataStartRowIndex) {

    int expectedRows = dataStartRowIndex - 1;

    SheetMeta sheetMeta = new SheetMetaBean("sheet", dataStartRowIndex);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("int1", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("int2", 2);

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    Sheet sheet = sheetComposer.compose(Collections.emptyList(), sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), expectedRows);
    for (int i = 0; i < expectedRows; i++) {
      Row row = sheet.getRows().get(i);
      assertEquals(row.getIndex(), i + 1);
      assertEquals(row.getCells().size(), sheetMeta.getFieldMetas().size());

      for (int j = 0; j < row.getCells().size(); j++) {
        Cell cell = row.getCells().get(j);
        assertEquals(cell.getIndex(), j + 1);
      }

    }

  }

  /**
   * 测试存在FieldMeta，有HeadMeta，没有Data
   * 预期结果：
   * 1) 存在 dataStartRowIndex - 1 数量的行
   * 2) 每行的cell数量为FieldMeta的数量
   */
  @Test(dataProvider = "sheetMetaBeanParam")
  public void testComposeSolidFieldMeta(int dataStartRowIndex) {

    int expectedRows = dataStartRowIndex - 1;

    SheetMeta sheetMeta = new SheetMetaBean("sheet", dataStartRowIndex);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("int1", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("int2", 2);

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    Sheet sheet = sheetComposer.compose(Collections.emptyList(), sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), expectedRows);
    for (int i = 0; i < expectedRows; i++) {
      Row row = sheet.getRows().get(i);
      assertEquals(row.getIndex(), i + 1);
      assertEquals(row.getCells().size(), sheetMeta.getFieldMetas().size());

      for (int j = 0; j < row.getCells().size(); j++) {
        Cell cell = row.getCells().get(j);
        assertEquals(cell.getIndex(), j + 1);
      }

    }

  }

  /**
   * 测试没有FieldMeta，有Data
   * 预期结果：
   * 1) 存在 dataStartRowIndex - 1 + data数量 的行
   * 2) 每行没有cell
   */
  @Test(dataProvider = "sheetMetaBeanParam")
  public void testComposeDataNoFieldMeta(int dataStartRowIndex) {

    SheetMeta sheetMeta = new SheetMetaBean("sheet", dataStartRowIndex);

    List datum = new ArrayList<>(2);
    datum.add(new Foo());
    datum.add(new Foo());

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    Sheet sheet = sheetComposer.compose(datum, sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), dataStartRowIndex - 1 + datum.size());
    for (Row row : sheet.getRows()) {
      assertEquals(row.getCells().size(), 0);
    }

  }

  /**
   * 测试有FieldMeta，有Data
   * 预期结果：
   * 1) 存在 dataStartRowIndex - 1 + data数量 的行
   * 2) 每行有cell
   */
  @Test
  public void testComposeDataFieldMeta() {

    int dataStartRowIndex = 2;
    SheetMeta sheetMeta = new SheetMetaBean("sheet", dataStartRowIndex);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("int1", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("int2", 2);

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);

    Foo t1 = new Foo();
    Foo t2 = new Foo();

    t1.setInt1(100);
    t1.setInt2(101);
    t2.setInt1(200);
    t2.setInt2(202);

    List datum = new ArrayList<>(2);
    datum.add(t1);
    datum.add(t2);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    sheetComposer.addFieldConverter(new NumberPropertyStringifier().matchField("int1"));
    sheetComposer.addFieldConverter(new NumberPropertyStringifier().matchField("int2"));
    Sheet sheet = sheetComposer.compose(datum, sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), dataStartRowIndex - 1 + datum.size());

    Row row1 = sheet.getRow(1);
    Row row2 = sheet.getRow(2);
    Row row3 = sheet.getRow(3);

    assertEquals(row1.getCells().size(), 2);
    assertEquals(row2.getCells().size(), 2);
    assertEquals(row3.getCells().size(), 2);

    assertNull(row1.getCell(1).getValue());
    assertNull(row1.getCell(2).getValue());

    assertEquals(row2.getCell(1).getValue(), "100");
    assertEquals(row2.getCell(2).getValue(), "101");

    assertEquals(row3.getCell(1).getValue(), "200");
    assertEquals(row3.getCell(2).getValue(), "202");

  }

  /**
   * 和 testComposeDataFieldMeta 一样只是column顺序反一反
   */
  @Test
  public void testComposeDataFieldMeta2() {

    int dataStartRowIndex = 2;
    SheetMeta sheetMeta = new SheetMetaBean("sheet", dataStartRowIndex);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("int1", 2);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("int2", 1);

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);

    Foo t1 = new Foo();
    Foo t2 = new Foo();

    t1.setInt1(100);
    t1.setInt2(101);
    t2.setInt1(200);
    t2.setInt2(202);

    List datum = new ArrayList<>(2);
    datum.add(t1);
    datum.add(t2);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    sheetComposer.addFieldConverter(new NumberPropertyStringifier().matchField("int1"));
    sheetComposer.addFieldConverter(new NumberPropertyStringifier().matchField("int2"));
    Sheet sheet = sheetComposer.compose(datum, sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), dataStartRowIndex - 1 + datum.size());

    Row row1 = sheet.getRow(1);
    Row row2 = sheet.getRow(2);
    Row row3 = sheet.getRow(3);

    assertEquals(row1.getCells().size(), 2);
    assertEquals(row2.getCells().size(), 2);
    assertEquals(row3.getCells().size(), 2);

    assertNull(row1.getCell(1).getValue());
    assertNull(row1.getCell(2).getValue());

    assertEquals(row2.getCell(1).getValue(), "101");
    assertEquals(row2.getCell(2).getValue(), "100");

    assertEquals(row3.getCell(1).getValue(), "202");
    assertEquals(row3.getCell(2).getValue(), "200");

  }

  /**
   * 测试有两个相同name的FieldMeta，有Data
   * 预期结果：
   * 1) 有3行数据
   * 2) 相同field的值出现两次，也就是有两个columnIndex不一样，但是值一样的Cell
   */
  @Test
  public void testComposeDuplicatedFieldMeta() {

    SheetMeta sheetMeta = new SheetMetaBean("sheet", 2);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("int1", 1);
    fieldMeta1.addHeaderMeta(new HeaderMetaBean(1, "int1字段"));

    FieldMetaBean fieldMeta2 = new FieldMetaBean("int1", 2);
    fieldMeta2.addHeaderMeta(new HeaderMetaBean(1, "int1字段-重复"));

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);

    Foo t1 = new Foo();
    Foo t2 = new Foo();

    t1.setInt1(100);
    t2.setInt1(200);

    List datum = new ArrayList<>(2);
    datum.add(t1);
    datum.add(t2);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    sheetComposer.addFieldConverter(new NumberPropertyStringifier().matchField("int1"));
    Sheet sheet = sheetComposer.compose(datum, sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), datum.size() + 1);

    Row row1 = sheet.getRow(1);
    Row row2 = sheet.getRow(2);
    Row row3 = sheet.getRow(3);

    assertEquals(row1.getCells().size(), 2);
    assertEquals(row1.getCell(1).getValue(), "int1字段");
    assertEquals(row1.getCell(2).getValue(), "int1字段-重复");

    assertEquals(row2.getCells().size(), 2);
    assertEquals(row2.getCell(1).getValue(), "100");
    assertEquals(row2.getCell(2).getValue(), "100");

    assertEquals(row3.getCells().size(), 2);
    assertEquals(row3.getCell(1).getValue(), "200");
    assertEquals(row3.getCell(2).getValue(), "200");

  }


  /**
   * 测试Compose 增加CellType
   * 预期结果：
   * Cell里面CellType和通过@{@link DefaultObject2SheetComposer#addFieldCellType}进去的CellType一致
   */
  @Test
  public void testComposeFieldCellTypeFieldMeta() {

    SheetMeta sheetMeta = new SheetMetaBean("sheet", 2);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("string", 1);
    fieldMeta1.addHeaderMeta(new HeaderMetaBean(1, "string字段"));

    sheetMeta.addFieldMeta(fieldMeta1);

    Foo t1 = new Foo();

    t1.setString("00100");

    List datum = new ArrayList<>(1);
    datum.add(t1);

    DefaultObject2SheetComposer sheetComposer = new DefaultObject2SheetComposer();
    sheetComposer.addFieldCellType("string", CellType.NUMERIC);
    Sheet sheet = sheetComposer.compose(datum, sheetMeta);

    assertEquals(sheet.getName(), "sheet");

    assertEquals(sheet.getRows().size(), datum.size() + 1);

    Row row1 = sheet.getRow(1);
    Row row2 = sheet.getRow(2);

    assertEquals(row1.getCells().size(), 1);
    assertEquals(row1.getCell(1).getValue(), "string字段");

    assertEquals(row2.getCells().size(), 1);
    assertEquals(row2.getCell(1).getValue(), "00100");
    assertEquals(row2.getCell(1).getCellType(), CellType.NUMERIC);


  }


}
