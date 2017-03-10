package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 纯数字校验器
 *
 * @see NumberUtils#isDigits(String)
 */
public class DigitsValidator extends CellValidatorTemplate<DigitsValidator> {

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return NumberUtils.isDigits(cell.getValue());
  }

}
