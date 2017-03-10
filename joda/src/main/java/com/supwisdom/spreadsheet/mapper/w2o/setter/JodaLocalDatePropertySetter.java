package com.supwisdom.spreadsheet.mapper.w2o.setter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * local date field value setter
 * Created by hanwen on 5/3/16.
 */
public class JodaLocalDatePropertySetter extends PropertySetterTemplate<Object, JodaLocalDatePropertySetter> {

  private String pattern;

  public JodaLocalDatePropertySetter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  protected LocalDate convertToProperty(String cellValue) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
    return dateTimeFormatter.parseLocalDate(cellValue);
  }

}
