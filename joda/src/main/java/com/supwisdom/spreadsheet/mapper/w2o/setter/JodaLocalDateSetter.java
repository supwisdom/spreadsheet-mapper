package com.supwisdom.spreadsheet.mapper.w2o.setter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * local date field value setter
 * Created by hanwen on 5/3/16.
 */
public class JodaLocalDateSetter extends FieldSetterTemplate<Object, JodaLocalDateSetter> {

  private String pattern;

  public JodaLocalDateSetter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  protected LocalDate convertProperty(String cellValue) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
    return dateTimeFormatter.parseLocalDate(cellValue);
  }

}
