package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * local date time field value setter
 * <p>
 * Created by hanwen on 5/3/16.
 */
public class LocalDateTimeSetter<T> extends CustomFieldSetter<T, LocalDateTimeSetter<T>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeSetter.class);

  private String pattern;

  public LocalDateTimeSetter<T> pattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

  @Override
  public void doSetValue(T object, Cell cell, FieldMeta fieldMeta) {
    try {
      DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
      String value = cell.getValue();
      String fieldName = fieldMeta.getName();

      LocalDateTime localDateTime = null;
      try {
        localDateTime = dateTimeFormatter.parseLocalDateTime(value);
      } catch (IllegalArgumentException e) {
        LOGGER.debug("{} format not validate", value);
      }
      PropertyUtils.setProperty(object, fieldName, localDateTime);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new Workbook2ObjectComposeException(e);
    }
  }
}
