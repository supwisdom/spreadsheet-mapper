package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * required validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class RequireValidator extends CustomSingleCellValidatorAdapter<RequireValidator> {

  @Override
  protected RequireValidator getThis() {
    return this;
  }

  @Override
  public boolean valid(Cell cell, FieldMeta fieldMeta) {
    return customValid(cell, fieldMeta);
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
    return StringUtils.isNotBlank(cell.getValue());
  }
}
