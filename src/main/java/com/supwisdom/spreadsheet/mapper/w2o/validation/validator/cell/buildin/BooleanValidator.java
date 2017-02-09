package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.param.BooleanParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.CustomSingleCellValidatorAdapter;

/**
 * boolean validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class BooleanValidator extends CustomSingleCellValidatorAdapter<BooleanValidator> {

  private BooleanParam param;

  public BooleanValidator param(BooleanParam param) {
    this.param = param;
    return this;
  }

  @Override
  protected BooleanValidator getThis() {
    return this;
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
    String value = cell.getValue();
    return param.getSupportedTrue().contains(value) || param.getSupportedFalse().contains(value);
  }
}
