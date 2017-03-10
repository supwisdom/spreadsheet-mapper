package com.supwisdom.spreadsheet.mapper.f2w.excel;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReader;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Excel2WorkbookReaderTest {

  @Test
  public void testReadXls() throws Exception {

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook workbook = reader.read(getClass().getResourceAsStream("test.xls"));

    assertWorkbookGood(workbook);

  }

  @Test
  public void testReadXlsx() throws Exception {

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook workbook = reader.read(getClass().getResourceAsStream("test.xlsx"));

    assertWorkbookGood(workbook);

  }

  private void assertWorkbookGood(Workbook workbook) throws ParseException {

    assertEquals(workbook.getSheets().size(), 1);
    Sheet sheet = workbook.getSheet(1);
    assertNotNull(sheet);
    assertEquals(sheet.getIndex(), 1);
    assertEquals(sheet.getName(), "Sheet0");
    assertEquals(sheet.getRows().size(), 2);

    Row row1 = sheet.getRow(1);
    assertNotNull(row1);
    assertEquals(row1.getCells().size(), 13);

    assertCellGood(row1.getCell(1), 1, "常规");
    assertCellGood(row1.getCell(2), 2, "数值");
    assertCellGood(row1.getCell(3), 3, "货币");
    assertCellGood(row1.getCell(4), 4, "会计专用");
    assertCellGood(row1.getCell(5), 5, "日期");
    assertCellGood(row1.getCell(6), 6, "时间");
    assertCellGood(row1.getCell(7), 7, "百分比");
    assertCellGood(row1.getCell(8), 8, "分数");
    assertCellGood(row1.getCell(9), 9, "科学计数");
    assertCellGood(row1.getCell(10), 10, "文本");
    assertCellGood(row1.getCell(11), 11, "特殊");
    assertCellGood(row1.getCell(12), 12, "自定义");
    assertCellGood(row1.getCell(13), 13, "布尔");

    Row row2 = sheet.getRow(2);
    assertNotNull(row2);
    assertEquals(row2.getCells().size(), 13);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

    assertCellGood(row2.getCell(1), 1, "树维");
    assertCellGood(row2.getCell(2), 2, "123456");
    assertCellGood(row2.getCell(3), 3, "-123.32");
    assertCellGood(row2.getCell(4), 4, "123.4");

    // Sun Feb 12 00:00:00 CST 2017
    simpleDateFormat.parse(row2.getCell(5).getValue());
    // Sun Dec 31 23:00:00 CST 1899
    simpleDateFormat.parse(row2.getCell(6).getValue());
    assertCellGood(row2.getCell(7), 7, "0.5");
    assertCellGood(row2.getCell(8), 8, "0.3");
    assertCellGood(row2.getCell(9), 9, "31008");
    assertCellGood(row2.getCell(10), 10, "文本2");
    assertCellGood(row2.getCell(11), 11, "31008");
    // Sun Dec 31 23:00:00 CST 1899
    simpleDateFormat.parse(row2.getCell(12).getValue());
    assertCellGood(row2.getCell(13), 13, "true");

  }

  private void assertCellGood(Cell cell, int expectedIndex, String expectedValue) {
    assertNotNull(cell);
    assertEquals(cell.getIndex(), expectedIndex);
    assertEquals(cell.getValue(), expectedValue);
  }

}
