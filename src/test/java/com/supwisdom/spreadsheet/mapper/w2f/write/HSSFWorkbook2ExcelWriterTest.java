package com.supwisdom.spreadsheet.mapper.w2f.write;

import com.supwisdom.spreadsheet.mapper.f2w.read.Excel2WorkbookReadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.f2w.read.WorkbookReadHelper;
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

    WorkbookWriteHelper workbookWriteHelper = new Workbook2ExcelWriteHelper(false);

    workbookWriteHelper.write(workbook, new FileOutputStream(file));

    WorkbookReadHelper reader = new Excel2WorkbookReadHelper();

    Workbook workbook1 = reader.read(new FileInputStream(file));

    AssertUtil.assertWorkbookEquals(workbook1, true);
  }

}
