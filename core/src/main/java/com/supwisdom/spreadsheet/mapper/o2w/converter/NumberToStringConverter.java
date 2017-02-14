package com.supwisdom.spreadsheet.mapper.o2w.converter;

import java.math.BigDecimal;

/**
 * plain number text value converter
 * Created by hanwen on 2017/1/4.
 */
public class NumberToStringConverter extends ToStringConverterTemplate<Object, NumberToStringConverter> {

  @Override
  public String convertProperty(Object property) {

    if (Integer.TYPE.equals(property.getClass())) {
      return String.valueOf(property);
    } else if (Double.TYPE.equals(property.getClass())) {
      return String.valueOf(property);
    } else if (Long.TYPE.equals(property.getClass())) {
      return String.valueOf(property);
    } else if (Float.TYPE.equals(property.getClass())) {
      return String.valueOf(property);
    } else if (Short.TYPE.equals(property.getClass())) {
      return String.valueOf(property);
    } else if (Byte.TYPE.equals(property.getClass())) {
      return String.valueOf(property);
    } else if (property instanceof BigDecimal) {
      return ((BigDecimal) property).stripTrailingZeros().toPlainString();
    } else if (property instanceof Double) {
      return BigDecimal.valueOf((Double) property).stripTrailingZeros().toPlainString();
    } else if (property instanceof Float) {
      return new BigDecimal(Float.toString((Float) property)).stripTrailingZeros().toPlainString();
    } else if (Number.class.isAssignableFrom(property.getClass())) {
      return property.toString();
    }
    throw new IllegalArgumentException(
        "Not a number: " + property.getClass().getName() + " value: " + property.toString());

  }

}
