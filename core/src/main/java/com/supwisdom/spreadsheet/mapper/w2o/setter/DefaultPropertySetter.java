package com.supwisdom.spreadsheet.mapper.w2o.setter;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 默认的FieldSetter，内部使用{@link BeanUtils#setProperty(Object, String, Object)}
 * Created by hanwen on 15-12-18.
 */
public class DefaultPropertySetter extends PropertySetterTemplate<Object, BooleanPropertySetter> {

  @Override
  protected Object convertToProperty(String cellValue) {
    return cellValue;
  }

}
