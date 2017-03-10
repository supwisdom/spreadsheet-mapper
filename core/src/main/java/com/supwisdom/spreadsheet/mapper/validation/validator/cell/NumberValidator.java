package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 判断是否数字的校验器
 * Created by hanwen on 2017/1/11.
 */
public class NumberValidator extends CellValidatorTemplate<NumberValidator> {

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return NumberUtils.isParsable(cell.getValue());
  }
}
