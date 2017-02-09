package com.supwisdom.spreadsheet.mapper.f2w.excel;

import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.io.InputStream;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "Excel2WorkbookReaderTest")
public class Excel2WorkbookReadHelperTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(Excel2WorkbookReadHelperTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test workbook read helper-------------------");
  }

  @Test
  public void testRead() throws Exception {

    DateFormatRegisterer.GLOBAL.registerFormat("[$-409]d\\-mmm\\-yy;@", "yyyy-MM-dd");
    DateFormatRegisterer.GLOBAL.registerFormat("mm/dd/yy;@", "yyyy-MM-dd");
    DateFormatRegisterer.GLOBAL.registerFormat("m/d/yy", "yyyy-MM-dd");

    InputStream is1 = getClass().getResourceAsStream("test.xls");

    WorkbookReadHelper reader = new Excel2WorkbookReadHelper();

    Workbook workbook1 = reader.read(is1);

    AssertUtil.assertWorkbookEquals(workbook1, true);

    InputStream is2 = getClass().getResourceAsStream("test.xlsx");

    Workbook workbook2 = reader.read(is2);

    AssertUtil.assertWorkbookEquals(workbook2, true);
  }

  @Test(dependsOnMethods = "testRead")
  public void testReadDate() throws Exception {

    InputStream is1 = getClass().getResourceAsStream("dateFormatTest.xlsx");

    WorkbookReadHelper reader = new Excel2WorkbookReadHelper();

    Workbook workbook = reader.read(is1);

    Sheet firstSheet = workbook.getFirstSheet();

    Row firstRow = firstSheet.getFirstRow();

    assertEquals(firstRow.getCell(1).getValue(), "1984-11-22");
    assertEquals(firstRow.getCell(2).getValue(), "1984-11-22");
    assertEquals(firstRow.getCell(3).getValue(), "1984-11-22");
    assertEquals(firstRow.getCell(4).getValue(), "1984-11-22");
  }
}
