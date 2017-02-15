package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * <pre>
 * field value setter adapter, easy implements customer value setter extends this.
 * extends this will skip custom setValue when cell value is blank (default blank value means no need setValue ).
 * </pre>
 * Created by hanwen on 15-12-16.
 */
public abstract class FieldSetterTemplate<T, V extends FieldSetterTemplate> implements FieldSetter<T> {

  static {
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

  private String matchField;

  final public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  @Override
  final public String getMatchField() {
    return matchField;
  }

  @Override
  public void setValue(T object, Cell cell, FieldMeta fieldMeta) {

    String value = cell.getValue();
    String field = fieldMeta.getName();

    try {
      if (value == null) {
        BeanUtils.setProperty(object, field, null);
      } else {
        BeanUtils.setProperty(object, field, convertProperty(value));
      }
    } catch (Exception e) {
      throw new Workbook2ObjectComposeException(e);
    }

  }

  /**
   * 将{@link Cell#getValue()}转换成object的某个property值
   *
   * @param cellValue 不会为null
   * @return
   */
  protected abstract Object convertProperty(String cellValue);

}
