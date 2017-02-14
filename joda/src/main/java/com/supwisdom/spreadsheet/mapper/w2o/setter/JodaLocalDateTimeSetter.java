package com.supwisdom.spreadsheet.mapper.w2o.setter;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * local date time field value setter
 * Created by hanwen on 5/3/16.
 */
public class JodaLocalDateTimeSetter extends FieldSetterTemplate<Object, JodaLocalDateTimeSetter> {

  private String pattern;

  public JodaLocalDateTimeSetter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  protected LocalDateTime convertProperty(String cellValue) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
    return dateTimeFormatter.parseLocalDateTime(cellValue);
  }

}
