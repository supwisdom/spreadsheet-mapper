package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TrueTestRowValidator implements RowValidator {

  private Counter counter;

  private Set<String> messageOnFields = new HashSet<>();

  public TrueTestRowValidator(Counter counter, String... messageOnFields) {
    this.counter = counter;
    Collections.addAll(this.messageOnFields, messageOnFields);
  }

  @Override
  public String getErrorMessage() {
    return "row error";
  }

  @Override
  public boolean validate(Row row, SheetMeta sheetMeta) {
    counter.hit();
    return true;
  }

  @Override
  public Set<String> getErrorFields() {
    return messageOnFields;
  }
}
