package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidatorTemplate;

import java.util.List;

public class TrueMCellValidator extends UnionCellValidatorTemplate<TrueMCellValidator> {

  private List<String> hitValidators;

  public TrueMCellValidator(List<String> hitValidators) {
    this.hitValidators = hitValidators;
  }

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {
    hitValidators.add("row:true:" + getGroup());
    return false;
  }

}
