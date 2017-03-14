package com.supwisdom.spreadsheet.mapper.validation.validator;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidatorTemplate;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecRecordCellValidator extends CellValidatorTemplate<ExecRecordCellValidator> {

  private final String name;

  private final boolean returnResult;

  private final ExecutionRecorder executionRecorder;

  public ExecRecordCellValidator(String name, boolean returnResult, ExecutionRecorder executionRecorder) {
    this.name = name;
    this.returnResult = returnResult;
    this.executionRecorder = executionRecorder;
  }

  @Override
  public boolean validate(Cell cell, FieldMeta fieldMeta) {
    executionRecorder.record(name + "#validate");
    return returnResult;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return returnResult;
  }

}
