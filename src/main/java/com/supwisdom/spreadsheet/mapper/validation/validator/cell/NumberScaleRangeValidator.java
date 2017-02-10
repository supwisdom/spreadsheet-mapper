package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * number scale range validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class NumberScaleRangeValidator extends CustomSingleCellValidatorAdapter<NumberScaleRangeValidator> {

  private final int gte;

  private final int lte;

  public NumberScaleRangeValidator(int gte, int lte) {
    this.gte = gte;
    this.lte = lte;
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

    return gte <= scale && scale <= lte;
  }
}
