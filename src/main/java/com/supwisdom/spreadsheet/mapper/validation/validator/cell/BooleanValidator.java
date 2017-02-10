package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Set;

/**
 * boolean validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class BooleanValidator extends CustomSingleCellValidatorAdapter<BooleanValidator> {

  private final Set<String> trueStrings;

  private final Set<String> falseStrings;

  public BooleanValidator(Set<String> trueStrings, Set<String> falseStrings) {
    this.trueStrings = trueStrings;
    this.falseStrings = falseStrings;
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
    String value = cell.getValue();
    return trueStrings.contains(value) || falseStrings.contains(value);
  }

}
