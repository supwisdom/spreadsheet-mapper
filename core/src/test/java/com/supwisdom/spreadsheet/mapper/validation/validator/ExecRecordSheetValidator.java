package com.supwisdom.spreadsheet.mapper.validation.validator;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidatorTemplate;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecRecordSheetValidator extends SheetValidatorTemplate<ExecRecordSheetValidator> {

  private final String name;

  private final boolean returnResult;

  private final ExecutionRecorder executionRecorder;

  public ExecRecordSheetValidator(String name, boolean returnResult, ExecutionRecorder executionRecorder) {
    this.name = name;
    this.returnResult = returnResult;
    this.executionRecorder = executionRecorder;
  }

  @Override
  public boolean validate(Sheet sheet, SheetMeta sheetMeta) {
    executionRecorder.record(name + "#validate");
    return returnResult;
  }

}
