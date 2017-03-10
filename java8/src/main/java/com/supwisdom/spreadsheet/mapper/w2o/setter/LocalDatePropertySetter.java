package com.supwisdom.spreadsheet.mapper.w2o.setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * local date field value setter
 * Created by hanwen on 5/3/16.
 */
public class LocalDatePropertySetter extends PropertySetterTemplate<Object, LocalDatePropertySetter> {

  private String pattern;

  public LocalDatePropertySetter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  protected LocalDate convertToProperty(String cellValue) {
    return LocalDate.parse(cellValue, DateTimeFormatter.ofPattern(pattern));
  }

}
