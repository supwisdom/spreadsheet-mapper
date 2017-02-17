package com.supwisdom.spreadsheet.mapper.w2o.setter;

import java.util.Set;

/**
 * 将字符串转换成Boolean值的{@link PropertySetter}
 * Created by hanwen on 5/3/16.
 */
public class BooleanPropertySetter extends PropertySetterTemplate<Object, BooleanPropertySetter> {

  private final Set<String> trueStrings;

  private final Set<String> falseStrings;

  /**
   * @param trueStrings  代表true的字符串
   * @param falseStrings 代表false的字符串
   */
  public BooleanPropertySetter(Set<String> trueStrings, Set<String> falseStrings) {
    this.trueStrings = trueStrings;
    this.falseStrings = falseStrings;
  }

  /**
   * @param cellValue 不会为null
   * @return 当cellValue既不在trueStrings中，也不在falseString中时，可能会返回null
   */
  @Override
  protected Object convertToProperty(String cellValue) {

    if (trueStrings.contains(cellValue)) {
      return Boolean.TRUE;
    } else if (falseStrings.contains(cellValue)) {
      return Boolean.FALSE;
    }
    return null;

  }

}
