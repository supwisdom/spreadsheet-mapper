package com.supwisdom.spreadsheet.mapper.w2o.validation;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.WorkbookBean;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.workbook.buildin.SheetSizeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static com.supwisdom.spreadsheet.mapper.w2o.validation.DefaultSheetValidationJobExceptionTest.getSheet;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test(dependsOnGroups = {"defaultSheetValidationHelperHitTest", "defaultSheetValidationHelperExceptionTest"})
public class DefaultWorkbookValidationJobTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultWorkbookValidationJobTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test workbook validation helper-------------------");
  }

  @Test
  public void testValid() throws Exception {

    DefaultSheetValidationJobExceptionTest.Counter counter = new DefaultSheetValidationJobExceptionTest.Counter();

    WorkbookValidationJob workbookValidationJob = new DefaultWorkbookValidationJob();

    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator1 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator1.matchField("int1");
    testCellValidator1.group("int1");
    sheetValidationJob.addDependencyValidator(testCellValidator1);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator2 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator2.matchField("int2");
    testCellValidator2.group("int2");
    sheetValidationJob.addDependencyValidator(testCellValidator2);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator3 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator3.matchField("long1");
    testCellValidator3.group("long1");
    sheetValidationJob.addDependencyValidator(testCellValidator3);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator4 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator4.matchField("long2");
    testCellValidator4.group("long2");
    sheetValidationJob.addDependencyValidator(testCellValidator4);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator5 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator5.matchField("float1");
    testCellValidator5.group("float1");
    sheetValidationJob.addDependencyValidator(testCellValidator5);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator6 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator6.matchField("float2");
    testCellValidator6.group("float2");
    sheetValidationJob.addDependencyValidator(testCellValidator6);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator7.matchField("string");
    testCellValidator7.group("string");
    sheetValidationJob.addDependencyValidator(testCellValidator6);

    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator1 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator1.group("int1");
    testMultiValidator1.matchFields("int1", "int2");
    sheetValidationJob.addDependencyValidator(testMultiValidator1);
    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator2 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator2.group("int2");
    testMultiValidator2.matchFields("int1", "int2");
    sheetValidationJob.addDependencyValidator(testMultiValidator2);


    Workbook workbook = new WorkbookBean();
    workbook.addSheet(getSheet());
    workbook.addSheet(getSheet());

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));

    boolean result = workbookValidationJob
        .addWorkbookValidator(new SheetSizeValidator().size(2))
        .addSheetValidationJob(sheetValidationJob)
        .addSheetValidationJob(sheetValidationJob)
        .valid(workbook, workbookMeta);

    assertTrue(result);
    assertEquals(counter.hitTime(), (7 + 2) * 2);
  }

}
