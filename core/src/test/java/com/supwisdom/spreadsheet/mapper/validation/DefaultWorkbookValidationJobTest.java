package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.core.WorkbookBean;
import com.supwisdom.spreadsheet.mapper.validation.validator.workbook.SheetAmountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;

import com.supwisdom.spreadsheet.mapper.validation.testvalidator.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test(dependsOnGroups = {"defaultSheetValidationJobTest"})
public class DefaultWorkbookValidationJobTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultWorkbookValidationJobTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test workbook validation job-------------------");
  }

  @Test
  public void testValid() throws Exception {

    Counter counter = new Counter();

    WorkbookValidationJob workbookValidationJob = new DefaultWorkbookValidationJob();

    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();
    TestCellValidator testCellValidator1 = new TestCellValidator(counter);
    testCellValidator1.matchField("int1");
    testCellValidator1.group("int1");
    sheetValidationJob.addValidator(testCellValidator1);
    TestCellValidator testCellValidator2 = new TestCellValidator(counter);
    testCellValidator2.matchField("int2");
    testCellValidator2.group("int2");
    sheetValidationJob.addValidator(testCellValidator2);
    TestCellValidator testCellValidator3 = new TestCellValidator(counter);
    testCellValidator3.matchField("long1");
    testCellValidator3.group("long1");
    sheetValidationJob.addValidator(testCellValidator3);
    TestCellValidator testCellValidator4 = new TestCellValidator(counter);
    testCellValidator4.matchField("long2");
    testCellValidator4.group("long2");
    sheetValidationJob.addValidator(testCellValidator4);
    TestCellValidator testCellValidator5 = new TestCellValidator(counter);
    testCellValidator5.matchField("float1");
    testCellValidator5.group("float1");
    sheetValidationJob.addValidator(testCellValidator5);
    TestCellValidator testCellValidator6 = new TestCellValidator(counter);
    testCellValidator6.matchField("float2");
    testCellValidator6.group("float2");
    sheetValidationJob.addValidator(testCellValidator6);
    TestCellValidator testCellValidator7 = new TestCellValidator(counter);
    testCellValidator7.matchField("string");
    testCellValidator7.group("string");
    sheetValidationJob.addValidator(testCellValidator6);

    TestMultiValidator testMultiValidator1 = new TestMultiValidator(counter);
    testMultiValidator1.group("int1");
    testMultiValidator1.matchFields("int1", "int2");
    sheetValidationJob.addValidator(testMultiValidator1);
    TestMultiValidator testMultiValidator2 = new TestMultiValidator(counter);
    testMultiValidator2.group("int2");
    testMultiValidator2.matchFields("int1", "int2");
    sheetValidationJob.addValidator(testMultiValidator2);


    Workbook workbook = new WorkbookBean();
    workbook.addSheet(getSheet());
    workbook.addSheet(getSheet());

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));

    boolean result = workbookValidationJob
        .addValidator(new SheetAmountValidator(2))
        .addSheetValidationJob(sheetValidationJob)
        .addSheetValidationJob(sheetValidationJob)
        .validate(workbook, workbookMeta);

    assertTrue(result);
    assertEquals(counter.hitTime(), (7 + 2) * 2);
  }

  private Sheet getSheet() {
    Sheet baseSheet = TestFactory.createSheet();
    Sheet sheet = new SheetBean();
    sheet.addRow(baseSheet.getRow(1));
    sheet.addRow(baseSheet.getRow(2));
    return sheet;
  }

}
