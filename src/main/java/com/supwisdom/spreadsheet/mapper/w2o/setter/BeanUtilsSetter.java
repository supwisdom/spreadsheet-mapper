package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.utils.FieldUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * using {@link BeanUtils#setProperty(Object, String, Object)}, this used as default setter
 * <p>
 * Created by hanwen on 15-12-18.
 */
public class BeanUtilsSetter {

  private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtilsSetter.class);

  {
    registerDefaultConverter();
  }

  public void set(Object object, Cell cell, FieldMeta fieldMeta) {
    try {
      String value = cell.getValue();
      if (StringUtils.isBlank(value) || !lookup(object, fieldMeta)) {
        return;
      }

      BeanUtils.setProperty(object, fieldMeta.getName(), value);

    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new Workbook2ObjectComposeException(e);
    }
  }

  private boolean lookup(Object object, FieldMeta fieldMeta) {
    Class fieldType = FieldUtils.getFieldType(object.getClass(), fieldMeta.getName().split("\\."));
    return fieldType != null && ConvertUtils.lookup(fieldType) != null;
  }

  private void registerDefaultConverter() {
    ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    ConvertUtils.register(new CalendarConverter(null), Calendar.class);
    ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
    ConvertUtils.register(new SqlTimeConverter(null), Time.class);
    ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
    ConvertUtils.register(new StringConverter(null), String.class);
    ConvertUtils.register(new BooleanConverter(null), Boolean.class);
    ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);
    ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
    ConvertUtils.register(new LongConverter(null), Long.class);
    ConvertUtils.register(new IntegerConverter(null), Integer.class);
    ConvertUtils.register(new DoubleConverter(null), Double.class);
    ConvertUtils.register(new FloatConverter(null), Float.class);
    ConvertUtils.register(new ShortConverter(null), Short.class);
    ConvertUtils.register(new ByteConverter(null), Byte.class);
  }
}
