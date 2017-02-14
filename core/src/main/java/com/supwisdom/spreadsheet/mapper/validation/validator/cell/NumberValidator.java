package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.apache.commons.lang3.math.NumberUtils;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * number validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class NumberValidator extends CustomCellValidator<NumberValidator> {

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return NumberUtils.isNumber(cell.getValue());
  }
}
