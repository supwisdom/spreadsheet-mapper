package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * 自定义{@link PropertySetter}的抽象类，只需实现{@link #convertToProperty(String)} 即可。
 * 需要注意的是，当{@link Cell#getValue()}是{@link StringUtils#isBlank(CharSequence)}的时候，会跳过执行{@link #convertToProperty(String)}。
 *
 * @param <T> 被设置property的Object的类型
 * @param <V> 自己的类型
 */
public abstract class PropertySetterTemplate<T, V extends PropertySetterTemplate> implements PropertySetter<T> {

  static {
    // FIXME 以下代码会影响到client code里的BeanUtils
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
  public void setProperty(T object, Cell cell, FieldMeta fieldMeta) {

    String value = cell.getValue();
    String field = fieldMeta.getName();

    try {
      if (value == null) {
        BeanUtils.setProperty(object, field, null);
      } else {
        BeanUtils.setProperty(object, field, convertToProperty(value));
      }
    } catch (Exception e) {
      throw new Workbook2ObjectComposeException(e);
    }

  }

  /**
   * 将{@link Cell#getValue()}转换成property Object
   *
   * @param cellValue 不会为null
   * @return property object
   */
  protected abstract Object convertToProperty(String cellValue);

}
