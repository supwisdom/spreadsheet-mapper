package com.supwisdom.spreadsheet.mapper.w2f.excel;

import com.supwisdom.spreadsheet.mapper.f2w.excel.Excel2WorkbookReader;
import com.supwisdom.spreadsheet.mapper.w2f.WorkbookWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReader;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "HSSFWorkbook2ExcelWriterTest", dependsOnGroups = "Excel2WorkbookReaderTest")
public class HSSFWorkbook2ExcelWriterTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(HSSFWorkbook2ExcelWriterTest.class);

  private File file;

  @BeforeClass
  public void before() throws IOException {
    LOGGER.debug("-------------------starting test hssf workbook write helper-------------------");
    file = File.createTempFile("test", ".xls");
    LOGGER.debug(file.getAbsolutePath());
  }

  @Test
  public void testWrite() throws Exception {

    Workbook workbook = TestFactory.createWorkbook();

    WorkbookWriter workbookWriter = new Workbook2ExcelWriter(false);

    workbookWriter.write(workbook, new FileOutputStream(file));

    WorkbookReader reader = new Excel2WorkbookReader();

    Workbook workbook1 = reader.read(new FileInputStream(file));

    AssertUtil.assertWorkbookEquals(workbook1, true);
  }

}
