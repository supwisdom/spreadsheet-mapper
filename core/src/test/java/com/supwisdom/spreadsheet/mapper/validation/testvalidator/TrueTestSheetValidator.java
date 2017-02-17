package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;

public class TrueTestSheetValidator implements SheetValidator {

  private Counter counter;

  public TrueTestSheetValidator(Counter counter) {
    this.counter = counter;
  }

  @Override
  public String getErrorMessage() {
    return "sheet error";
  }

  @Override
  public boolean validate(Sheet sheet, SheetMeta sheetMeta) {
    counter.hit();
    ;
    return true;
  }
}
