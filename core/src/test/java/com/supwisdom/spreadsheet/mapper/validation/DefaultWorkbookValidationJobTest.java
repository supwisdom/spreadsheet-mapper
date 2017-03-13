package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.core.WorkbookBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;
import com.supwisdom.spreadsheet.mapper.validation.validator.ExecRecordWorkbookValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.ExecutionRecorder;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/3/13.
 */
public class DefaultWorkbookValidationJobTest {

  @DataProvider
  public Object[][] provideWorkbookAndMeta() {

    Workbook workbook = new WorkbookBean();

    Sheet sheet1 = new SheetBean("Sheet-1");
    workbook.addSheet(sheet1);

    Sheet sheet2 = new SheetBean("Sheet-2");
    workbook.addSheet(sheet2);

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    SheetMeta sheetMeta1 = new SheetMetaBean(1);
    SheetMeta sheetMeta2 = new SheetMetaBean(2);
    workbookMeta.addSheetMeta(sheetMeta1);
    workbookMeta.addSheetMeta(sheetMeta2);

    return new Object[][] {
        new Object[] { workbook, workbookMeta }
    };

  }

  /**
   * 测试 WorkbookValidator, SheetValidationJob 校验顺序
   */
  @Test(dataProvider = "provideWorkbookAndMeta")
  public void testDifferentKindValidatorExecutionOrder(Workbook workbook, WorkbookMeta workbookMeta) {

    DefaultWorkbookValidationJob validationJob = new DefaultWorkbookValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(new ExecRecordWorkbookValidator("wb-validator", true, executionRecorder));
    validationJob.addSheetValidationJob(new ExecRecordSheetValidationJob("sheet-validator-job-1", true, executionRecorder));
    validationJob.addSheetValidationJob(new ExecRecordSheetValidationJob("sheet-validator-job-2", true, executionRecorder));
    validationJob.validate(workbook, workbookMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "wb-validator#validate,sheet-validator-job-1#validate,sheet-validator-job-2#validate"
    );

  }

  /**
   * 测试 一个 SheetValidationJob 的失败不影响另一个
   */
  @Test(dataProvider = "provideWorkbookAndMeta")
  public void testDifferentSheetValidationJobFailNotInfluence(Workbook workbook, WorkbookMeta workbookMeta) {

    DefaultWorkbookValidationJob validationJob = new DefaultWorkbookValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(new ExecRecordWorkbookValidator("wb-validator", true, executionRecorder));
    validationJob.addSheetValidationJob(new ExecRecordSheetValidationJob("sheet-validator-job-1", false, executionRecorder));
    validationJob.addSheetValidationJob(new ExecRecordSheetValidationJob("sheet-validator-job-2", true, executionRecorder));
    validationJob.validate(workbook, workbookMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "wb-validator#validate,sheet-validator-job-1#validate,sheet-validator-job-2#validate"
    );

  }

}
