package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.CustomUnionCellValidator;

import java.util.List;

public class TestMultiValidator extends CustomUnionCellValidator<TestMultiValidator> {

  private Counter counter;

  public TestMultiValidator() {
  }

  public TestMultiValidator(Counter counter) {
    this.counter = counter;
  }

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {
    if (counter != null) {
      counter.hit();
    }
    return true;
  }

}
