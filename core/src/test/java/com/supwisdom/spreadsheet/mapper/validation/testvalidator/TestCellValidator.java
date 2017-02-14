package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CustomCellValidator;

public class TestCellValidator extends CustomCellValidator<TestCellValidator> {

  private Counter counter;

  public TestCellValidator() {
  }

  public TestCellValidator(Counter counter) {
    this.counter = counter;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    if (counter != null) {
      counter.hit();
    }
    return true;
  }

}
