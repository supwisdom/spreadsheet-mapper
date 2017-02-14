package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.o2w.Object2WorkbookComposeException;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param <T> 要转换成{@link Row}的对象的类型
 * @param <V> 本类的类型
 */
public abstract class ToStringConverterTemplate<T, V extends ToStringConverterTemplate>
    implements ToStringConverter<T> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  protected String matchField;

  protected String nullString;

  /**
   * 匹配哪个{@link FieldMeta#getName()}
   *
   * @param matchField
   * @return
   */
  public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  /**
   * 如果object的property为null时，返回怎样的字符串的设置
   *
   * @param nullString
   * @return
   */
  public V nullString(String nullString) {
    this.nullString = nullString;
    return (V) this;
  }

  @Override
  public String getMatchField() {
    return matchField;
  }

  @Override
  public String getString(T object, FieldMeta fieldMeta) {

    String fieldName = fieldMeta.getName();
    Object property = null;
    try {
      property = PropertyUtils.getProperty(object, fieldName);
    } catch (NestedNullException e) {
      logger.debug("Nested property is null", e);
    } catch (Exception e) {
      throw new Object2WorkbookComposeException("Sheet compose error", e);
    }

    if (property != null) {
      return convertProperty(property);
    }
    return nullString;

  }

  /**
   * 将object的某个property转换成String
   *
   * @param property 要转换成String的某个property，不会为null
   * @return
   */
  protected abstract String convertProperty(Object property);

}
