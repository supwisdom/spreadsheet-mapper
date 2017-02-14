package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * boolean field value setter
 * <p>
 * Created by hanwen on 5/3/16.
 */
public class BooleanSetter<T> extends CustomFieldSetter<T, BooleanSetter<T>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BooleanSetter.class);

  private final Set<String> trueStrings;

  private final Set<String> falseStrings;

  public BooleanSetter(Set<String> trueStrings, Set<String> falseStrings) {
    this.trueStrings = trueStrings;
    this.falseStrings = falseStrings;
  }

  @Override
  public void doSetValue(T object, Cell cell, FieldMeta fieldMeta) {
    try {
      String stringValue = cell.getValue();
      Boolean booleanValue = null;

      if (trueStrings.contains(stringValue)) {
        booleanValue = Boolean.TRUE;
      } else if (falseStrings.contains(stringValue)) {
        booleanValue = Boolean.FALSE;
      }

      BeanUtils.setProperty(object, fieldMeta.getName(), booleanValue);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new Workbook2ObjectComposeException(e);
    }
  }
}
