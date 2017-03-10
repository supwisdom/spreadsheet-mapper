package com.supwisdom.spreadsheet.mapper.w2o.setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * local date time field value setter
 * Created by hanwen on 5/3/16.
 */
public class LocalDateTimePropertySetter extends PropertySetterTemplate<Object, LocalDateTimePropertySetter> {

  private String pattern;

  public LocalDateTimePropertySetter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  protected LocalDateTime convertToProperty(String cellValue) {
    return LocalDateTime.parse(cellValue, DateTimeFormatter.ofPattern(pattern));
  }

}
