package com.supwisdom.spreadsheet.mapper.validation.validator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidatorTemplate;

import java.util.List;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecRecordUnionCellValidator extends UnionCellValidatorTemplate<ExecRecordUnionCellValidator> {

  private final String name;

  private final boolean returnResult;

  private final ExecutionRecorder executionRecorder;

  public ExecRecordUnionCellValidator(String name, boolean returnResult, ExecutionRecorder executionRecorder) {
    this.name = name;
    this.returnResult = returnResult;
    this.executionRecorder = executionRecorder;
  }

  @Override
  public boolean validate(List<Cell> cells, List<FieldMeta> fieldMetas) {
    executionRecorder.record(name + "#validate");
    return returnResult;
  }

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {
    return returnResult;
  }

}
