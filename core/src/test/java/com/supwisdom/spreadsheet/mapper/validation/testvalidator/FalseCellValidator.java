package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidatorTemplate;

import java.util.List;

public class FalseCellValidator extends CellValidatorTemplate<FalseCellValidator> {

  private List<String> hitValidators;

  public FalseCellValidator(List<String> hitValidators) {
    this.hitValidators = hitValidators;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    hitValidators.add("cell:false:" + getGroup());
    return false;
  }

}
