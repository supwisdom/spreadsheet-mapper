package com.supwisdom.spreadsheet.mapper.bean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * 默认的{@link BeanUtilsBeanFactory}
 * Created by qianjia on 2017/3/13.
 */
public class DefaultBeanUtilsBeanFactory implements BeanUtilsBeanFactory {

  private static final BeanUtilsBean BEAN_UTILS_BEAN;

  static {
    BEAN_UTILS_BEAN = new BeanUtilsBean();
    ConvertUtilsBean convertUtils = BEAN_UTILS_BEAN.getConvertUtils();

    convertUtils.register(new DateConverter(null), java.util.Date.class);
    convertUtils.register(new CalendarConverter(null), Calendar.class);
    convertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
    convertUtils.register(new SqlTimeConverter(null), Time.class);
    convertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
    convertUtils.register(new StringConverter(null), String.class);
    convertUtils.register(new BooleanConverter(null), Boolean.class);
    convertUtils.register(new BigIntegerConverter(null), BigInteger.class);
    convertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
    convertUtils.register(new LongConverter(null), Long.class);
    convertUtils.register(new IntegerConverter(null), Integer.class);
    convertUtils.register(new DoubleConverter(null), Double.class);
    convertUtils.register(new FloatConverter(null), Float.class);
    convertUtils.register(new ShortConverter(null), Short.class);
    convertUtils.register(new ByteConverter(null), Byte.class);
  }

  @Override
  public BeanUtilsBean getInstance() {
    return BEAN_UTILS_BEAN;
  }

}
