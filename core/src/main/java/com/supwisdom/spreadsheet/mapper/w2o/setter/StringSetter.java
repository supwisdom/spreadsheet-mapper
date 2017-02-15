package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;

/**
 * 返回和{@link Cell#getValue()}值一样的FieldSetter
 */
public class StringSetter extends FieldSetterTemplate<Object, StringSetter> {

  @Override
  protected Object convertProperty(String cellValue) {
    return cellValue;

  }

}
