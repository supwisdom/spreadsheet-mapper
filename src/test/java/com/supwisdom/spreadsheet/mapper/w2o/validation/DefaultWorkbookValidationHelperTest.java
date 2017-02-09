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
import static com.supwisdom.spreadsheet.mapper.w2o.validation.DefaultSheetValidationHelperExceptionTest.getSheet;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test(dependsOnGroups = {"defaultSheetValidationHelperHitTest", "defaultSheetValidationHelperExceptionTest"})
public class DefaultWorkbookValidationHelperTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultWorkbookValidationHelperTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test workbook validation helper-------------------");
  }

  @Test
  public void testValid() throws Exception {

    DefaultSheetValidationHelperExceptionTest.Counter counter = new DefaultSheetValidationHelperExceptionTest.Counter();

    WorkbookValidationHelper workbookValidationHelper = new DefaultWorkbookValidationHelper();

    SheetValidationHelper sheetValidationHelper = new DefaultSheetValidationHelper();
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator1 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator1.matchField("int1");
    testCellValidator1.group("int1");
    sheetValidationHelper.addDependencyValidator(testCellValidator1);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator2 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator2.matchField("int2");
    testCellValidator2.group("int2");
    sheetValidationHelper.addDependencyValidator(testCellValidator2);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator3 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator3.matchField("long1");
    testCellValidator3.group("long1");
    sheetValidationHelper.addDependencyValidator(testCellValidator3);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator4 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator4.matchField("long2");
    testCellValidator4.group("long2");
    sheetValidationHelper.addDependencyValidator(testCellValidator4);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator5 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator5.matchField("float1");
    testCellValidator5.group("float1");
    sheetValidationHelper.addDependencyValidator(testCellValidator5);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator6 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator6.matchField("float2");
    testCellValidator6.group("float2");
    sheetValidationHelper.addDependencyValidator(testCellValidator6);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator7.matchField("string");
    testCellValidator7.group("string");
    sheetValidationHelper.addDependencyValidator(testCellValidator6);

    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator1 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator1.group("int1");
    testMultiValidator1.matchFields("int1", "int2");
    sheetValidationHelper.addDependencyValidator(testMultiValidator1);
    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator2 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator2.group("int2");
    testMultiValidator2.matchFields("int1", "int2");
    sheetValidationHelper.addDependencyValidator(testMultiValidator2);


    Workbook workbook = new WorkbookBean();
    workbook.addSheet(getSheet());
    workbook.addSheet(getSheet());

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));

    boolean result = workbookValidationHelper
        .addWorkbookValidator(new SheetSizeValidator().size(2))
        .addSheetValidationHelper(sheetValidationHelper)
        .addSheetValidationHelper(sheetValidationHelper)
        .valid(workbook, workbookMeta);

    assertTrue(result);
    assertEquals(counter.hitTime(), (7 + 2) * 2);
  }

}
