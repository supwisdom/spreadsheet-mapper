package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 正则表达式校验器
 * Created by hanwen on 2017/1/11.
 */
public class RegexFormatValidator extends CellValidatorTemplate<RegexFormatValidator> {

  private String regex;

  public RegexFormatValidator(String regex) {
    this.regex = regex;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return cell.getValue().matches(regex);
  }
}
