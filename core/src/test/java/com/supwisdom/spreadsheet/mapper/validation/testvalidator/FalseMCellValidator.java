package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.CustomUnionCellValidator;

import java.util.List;

public class FalseMCellValidator extends CustomUnionCellValidator<FalseMCellValidator> {

  private List<String> hitValidators;

  public FalseMCellValidator(List<String> hitValidators) {
    this.hitValidators = hitValidators;
  }

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {
    hitValidators.add("row:false:" + getGroup());
    return false;
  }

}
