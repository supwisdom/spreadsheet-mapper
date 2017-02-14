package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CustomCellValidator;

import java.util.List;

public class TrueCellValidator extends CustomCellValidator<TrueCellValidator> {

  private List<String> hitValidators;

  public TrueCellValidator(List<String> hitValidators) {
    this.hitValidators = hitValidators;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    hitValidators.add("cell:true:" + getGroup());
    return true;
  }

}
