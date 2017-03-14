package com.supwisdom.spreadsheet.mapper.validation.validator;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidatorTemplate;

import java.util.Set;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecRecordRowValidator extends RowValidatorTemplate<ExecRecordRowValidator> {

  private final String name;

  private final boolean returnResult;

  private final ExecutionRecorder executionRecorder;

  public ExecRecordRowValidator(String name, boolean returnResult, ExecutionRecorder executionRecorder) {
    this.name = name;
    this.returnResult = returnResult;
    this.executionRecorder = executionRecorder;
  }

  @Override
  public boolean validate(Row row, SheetMeta sheetMeta) {
    executionRecorder.record(name + "#validate");
    return returnResult;
  }

  @Override
  public Set<String> getErrorFields() {
    return null;
  }

}
