package com.supwisdom.spreadsheet.mapper.o2w.converter;

import java.util.Objects;

/**
 * boolean readable text value converter
 * Created by hanwen on 16/3/18.
 */
public class DefaultPropertyStringifier extends PropertyStringifierTemplate<Object, DefaultPropertyStringifier> {

  @Override
  public String convertProperty(Object property) {

    return Objects.toString(property, nullString);

  }

}
