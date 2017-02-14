package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

/**
 * required validator
 * Created by hanwen on 2017/1/11.
 */
public class RequireValidator extends CustomCellValidator<RequireValidator> {

  @Override
  final public boolean validate(Cell cell, FieldMeta fieldMeta) {
    return doValidate(cell, fieldMeta);
  }

  @Override
  final protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return StringUtils.isNotBlank(cell.getValue());
  }

}
