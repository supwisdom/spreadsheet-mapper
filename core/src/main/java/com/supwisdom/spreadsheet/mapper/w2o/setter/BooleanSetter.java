package com.supwisdom.spreadsheet.mapper.w2o.setter;

import java.util.Set;

/**
 * boolean field value setter
 * Created by hanwen on 5/3/16.
 */
public class BooleanSetter extends FieldSetterTemplate<Object, BooleanSetter> {

  private final Set<String> trueStrings;

  private final Set<String> falseStrings;

  public BooleanSetter(Set<String> trueStrings, Set<String> falseStrings) {
    this.trueStrings = trueStrings;
    this.falseStrings = falseStrings;
  }

  @Override
  protected Object convertProperty(String cellValue) {

    if (trueStrings.contains(cellValue)) {
      return Boolean.TRUE;
    } else if (falseStrings.contains(cellValue)) {
      return Boolean.FALSE;
    }
    return null;

  }

}
