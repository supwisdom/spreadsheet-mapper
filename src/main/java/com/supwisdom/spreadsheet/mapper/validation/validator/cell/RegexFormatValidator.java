package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * regex format validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class RegexFormatValidator extends CustomSingleCellValidatorAdapter<RegexFormatValidator> {

  private String regex;

  public RegexFormatValidator regex(String regex) {
    this.regex = regex;
    return this;
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
    return cell.getValue().matches(regex);
  }
}
