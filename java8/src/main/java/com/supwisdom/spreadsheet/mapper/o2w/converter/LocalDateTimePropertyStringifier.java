package com.supwisdom.spreadsheet.mapper.o2w.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * local date time text value with supplied pattern converter
 * Created by hanwen on 5/3/16.
 */
public class LocalDateTimePropertyStringifier
    extends PropertyStringifierTemplate<LocalDateTime, LocalDateTimePropertyStringifier> {

  private String pattern;

  public LocalDateTimePropertyStringifier(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public String convertProperty(Object property) {

    if (!LocalDateTime.class.equals(property.getClass())) {
      throw new IllegalArgumentException(
          "Not a LocalDateTime: " + property.getClass().getName() + " value: " + property.toString());
    }
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    return ((LocalDateTime) property).format(dateTimeFormatter);

  }

}
