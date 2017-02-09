package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.o2w.Object2WorkbookComposeException;

import java.math.BigDecimal;

/**
 * plain number text value converter
 * <p>
 * Created by hanwen on 2017/1/4.
 */
public class PlainNumberConverter<T> extends FieldConverterAdapter<T, PlainNumberConverter<T>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(PlainNumberConverter.class);

  @Override
  protected PlainNumberConverter<T> getThis() {
    return this;
  }

  @Override
  public String getValue(T object, Cell cell, FieldMeta fieldMeta) {

    try {
      Object value = PropertyUtils.getProperty(object, fieldMeta.getName());

      if (!(value instanceof Number)) {
        return null;
      }

      if (value instanceof BigDecimal) {
        return ((BigDecimal) value).stripTrailingZeros().toPlainString();
      } else if (value instanceof Double) {
        return BigDecimal.valueOf((Double) value).stripTrailingZeros().toPlainString();
      } else if (value instanceof Float) {
        return new BigDecimal(Float.toString((Float) value)).stripTrailingZeros().toPlainString();
      }
      return value.toString();

    } catch (NestedNullException e) {
      LOGGER.debug("{} is null", fieldMeta.getName());
      return null;
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new Object2WorkbookComposeException(e);
    }
  }
}
