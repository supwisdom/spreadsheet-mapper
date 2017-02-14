package com.supwisdom.spreadsheet.mapper.o2w.converter;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.o2w.Object2WorkbookComposeException;

/**
 * local date text value with supplied pattern converter
 * <p>
 * Created by hanwen on 5/3/16.
 */
public class LocalDateConverter<T> extends FieldConverterAdapter<T, LocalDateConverter<T>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateConverter.class);

  private String pattern;

  public LocalDateConverter<T> pattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

  @Override
  public String getValue(T object, Cell cell, FieldMeta fieldMeta) {

    try {
      Object value = PropertyUtils.getProperty(object, fieldMeta.getName());

      if (!(value instanceof LocalDate)) {
        return null;
      }

      return ((LocalDate) value).toString(pattern);

    } catch (NestedNullException e) {
      LOGGER.debug("{} is null", fieldMeta.getName());
      return null;
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new Object2WorkbookComposeException(e);
    }
  }
}
