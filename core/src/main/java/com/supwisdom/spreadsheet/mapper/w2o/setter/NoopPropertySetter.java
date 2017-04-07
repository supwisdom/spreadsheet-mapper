package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 什么都不做的PropertySetter
 * Created by qianjia on 2017/4/7.
 */
public class NoopPropertySetter implements PropertySetter {

  private final String matchField;

  public NoopPropertySetter(String matchField) {
    this.matchField = matchField;
  }

  @Override
  public String getMatchField() {
    return matchField;
  }

  @Override
  public void setProperty(Object object, Cell cell, FieldMeta fieldMeta) {
    // do nothing
  }
}
