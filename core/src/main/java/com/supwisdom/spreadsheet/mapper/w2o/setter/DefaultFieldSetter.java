package com.supwisdom.spreadsheet.mapper.w2o.setter;

import org.apache.commons.beanutils.BeanUtils;

/**
 * using {@link BeanUtils#setProperty(Object, String, Object)}, this used as default setter
 * Created by hanwen on 15-12-18.
 */
public class DefaultFieldSetter extends FieldSetterTemplate<Object, BooleanSetter> {


  @Override
  protected Object convertProperty(String cellValue) {
    return cellValue;
  }

}
