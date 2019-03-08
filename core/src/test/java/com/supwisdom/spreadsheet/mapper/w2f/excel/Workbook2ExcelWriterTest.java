package com.supwisdom.spreadsheet.mapper.w2f.excel;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReader;
import com.supwisdom.spreadsheet.mapper.f2w.excel.Excel2WorkbookReader;
import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.w2f.WorkbookWriter;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/3/14.
 */
public class Workbook2ExcelWriterTest {

  @DataProvider
  public Object[][] provideWorkbook() {

    Workbook workbook = new WorkbookBean();

    for (int i = 0; i < 2; i++) {
      Sheet sheet = new SheetBean("Sheet" + i);
      for (int j = 0; j < 2; j++) {
        Row row = new RowBean();
        for (int k = 0; k < 4; k++) {
//          row.addCell(new CellBean(RandomStringUtils.random(RandomUtils.nextInt(1, 10), 0x4e00, 0x9fa5, false, false)));
          if (k == 0) {
            row.addCell(new CellBean("代码"));
          }
          if (k == 1) {
            row.addCell(new CellBean("code"));
          }
        }
        sheet.addRow(row);
      }
      workbook.addSheet(sheet);
    }

    return new Object[][]{
        new Object[]{workbook}
    };

  }


  @DataProvider
  public Object[][] provideWorkbook1() {

    Workbook workbook = new WorkbookBean();

    for (int i = 0; i < 2; i++) {
      Sheet sheet = new SheetBean("Sheet" + i);
      for (int j = 0; j < 2; j++) {
        Row row = new RowBean();
        for (int k = 0; k < 2; k++) {
//          row.addCell(new CellBean(RandomStringUtils.random(RandomUtils.nextInt(1, 10), 0x4e00, 0x9fa5, false, false)));
          if (k == 0) {
            row.addCell(new CellBean("00100", CellType.NUMERIC));
          }
          if (k == 1) {
            row.addCell(new CellBean("12.5", CellType.NUMERIC));
          }
        }
        sheet.addRow(row);
      }
      workbook.addSheet(sheet);
    }

    return new Object[][]{
        new Object[]{workbook}
    };

  }


  @Test(dataProvider = "provideWorkbook")
  public void testWriteXls(Workbook workbook) throws Exception {

    File file = File.createTempFile("workbook-2-excel", ".xls");
    file.deleteOnExit();

    WorkbookWriter workbookWriter = new Workbook2ExcelWriter(false);

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      workbookWriter.write(workbook, outputStream);
    }

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook readWorkbook = reader.read(new FileInputStream(file));

    assertEquals(readWorkbook, workbook);

  }

  @Test(dataProvider = "provideWorkbook")
  public void testWriteXlsx(Workbook workbook) throws Exception {

    File file = File.createTempFile("workbook-2-excel", ".xlsx");
    file.deleteOnExit();

    WorkbookWriter workbookWriter = new Workbook2ExcelWriter();

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      workbookWriter.write(workbook, outputStream);
    }

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook readWorkbook = reader.read(new FileInputStream(file));

    assertEquals(readWorkbook, workbook);


  }


  @Test(dataProvider = "provideWorkbook1")
  public void testWriteXls1(Workbook workbook) throws Exception {

    File file = File.createTempFile("workbook-2-excel", ".xls");
    file.deleteOnExit();

    WorkbookWriter workbookWriter = new Workbook2ExcelWriter(false);

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      workbookWriter.write(workbook, outputStream);
    }

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook readWorkbook = reader.read(new FileInputStream(file));

    List<Cell> cells = readWorkbook.getFirstSheet().getFirstRow().getCells();

    assertEquals(cells.get(0).getValue(), "100");
    assertEquals(cells.get(1).getValue(), "12.5");

  }

  @Test(dataProvider = "provideWorkbook1")
  public void testWriteXlsx1(Workbook workbook) throws Exception {

    File file = File.createTempFile("workbook-2-excel", ".xlsx");
    file.deleteOnExit();

    WorkbookWriter workbookWriter = new Workbook2ExcelWriter();

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      workbookWriter.write(workbook, outputStream);
    }

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook readWorkbook = reader.read(new FileInputStream(file));


    List<Cell> cells = readWorkbook.getFirstSheet().getFirstRow().getCells();

    assertEquals(cells.get(0).getValue(), "100");
    assertEquals(cells.get(1).getValue(), "12.5");


  }


}
