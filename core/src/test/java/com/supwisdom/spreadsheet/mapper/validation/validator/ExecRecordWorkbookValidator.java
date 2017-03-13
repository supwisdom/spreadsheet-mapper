package com.supwisdom.spreadsheet.mapper.validation.validator;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.workbook.WorkbookValidatorTemplate;

import java.util.Set;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecRecordWorkbookValidator extends WorkbookValidatorTemplate<ExecRecordWorkbookValidator> {

  private final String name;

  private final boolean returnResult;

  private final ExecutionRecorder executionRecorder;

  public ExecRecordWorkbookValidator(String name, boolean returnResult, ExecutionRecorder executionRecorder) {
    this.name = name;
    this.returnResult = returnResult;
    this.executionRecorder = executionRecorder;
  }

  @Override
  public boolean validate(Workbook workbook, WorkbookMeta workbookMeta) {
    executionRecorder.record(name + "#validate");
    return returnResult;
  }

  @Override
  public Set<Integer> getErrorSheetIndices() {
    return null;
  }

}
