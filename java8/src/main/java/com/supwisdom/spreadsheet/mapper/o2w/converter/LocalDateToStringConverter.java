package com.supwisdom.spreadsheet.mapper.o2w.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * local date text value with supplied pattern converter
 * Created by hanwen on 5/3/16.
 */
public class LocalDateToStringConverter extends ToStringConverterTemplate<LocalDate, LocalDateToStringConverter> {

  private String pattern;

  public LocalDateToStringConverter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public String convertProperty(Object property) {

    if (!LocalDate.class.equals(property.getClass())) {
      throw new IllegalArgumentException(
          "Not a LocalDate: " + property.getClass().getName() + " value: " + property.toString());
    }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    return ((LocalDate) property).format(dateTimeFormatter);
  }

}
