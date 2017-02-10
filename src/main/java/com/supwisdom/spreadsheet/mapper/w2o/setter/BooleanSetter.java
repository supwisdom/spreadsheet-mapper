package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.validation.builder.BooleanParam;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * boolean field value setter
 * <p>
 * Created by hanwen on 5/3/16.
 */
public class BooleanSetter<T> extends FieldSetterAdapter<T, BooleanSetter<T>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BooleanSetter.class);

  private BooleanParam param;

  public BooleanSetter<T> param(BooleanParam param) {
    this.param = param;
    return this;
  }

  @Override
  protected BooleanSetter<T> getThis() {
    return this;
  }

  @Override
  public void customSet(T object, Cell cell, FieldMeta fieldMeta) {
    try {
      String stringValue = cell.getValue();
      Boolean booleanValue = null;

      if (param.getTrueStrings().contains(stringValue)) {
        booleanValue = Boolean.TRUE;
      } else if (param.getFalseStrings().contains(stringValue)) {
        booleanValue = Boolean.FALSE;
      }

      BeanUtils.setProperty(object, fieldMeta.getName(), booleanValue);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new Workbook2ObjectComposeException(e);
    }
  }
}
