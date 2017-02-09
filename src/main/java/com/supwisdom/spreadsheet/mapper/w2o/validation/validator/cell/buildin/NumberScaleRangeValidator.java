package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.w2o.param.NumberScaleRangeParam;
import org.apache.commons.lang3.math.NumberUtils;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.CustomSingleCellValidatorAdapter;

/**
 * number scale range validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class NumberScaleRangeValidator extends CustomSingleCellValidatorAdapter<NumberScaleRangeValidator> {

  private NumberScaleRangeParam param;

  public NumberScaleRangeValidator param(NumberScaleRangeParam param) {
    this.param = param;
    return this;
  }

  @Override
  protected NumberScaleRangeValidator getThis() {
    return this;
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
    String value = cell.getValue();

    if (!NumberUtils.isNumber(value)) {
      return false;
    }

    String[] numberPlace = value.split("\\.");

    int scale = 0;
    if (numberPlace.length != 1) {
      scale = numberPlace[1].length();
    }

    return param.getGte() <= scale && scale <= param.getLte();
  }
}
