package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.bean.BeanUtilsBeanFactory;
import com.supwisdom.spreadsheet.mapper.bean.DefaultBeanUtilsBeanFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.o2w.Object2WorkbookComposeException;
import jodd.bean.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.NestedNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param <T> 要转换成{@link Row}的Object的类型
 * @param <V> 本类的类型
 */
public abstract class PropertyStringifierTemplate<T, V extends PropertyStringifierTemplate>
    implements PropertyStringifier<T> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  protected BeanUtilsBeanFactory beanUtilsBeanFactory = new DefaultBeanUtilsBeanFactory();

  protected String matchField;

  protected String nullString;

  /**
   * 设置新的 {@link BeanUtilsBeanFactory}
   *
   * @param beanUtilsBeanFactory
   * @return
   */
  final public V beanUtilsBeanFactory(BeanUtilsBeanFactory beanUtilsBeanFactory) {
    this.beanUtilsBeanFactory = beanUtilsBeanFactory;
    return (V) this;
  }

  /**
   * 匹配哪个{@link FieldMeta#getName()}
   *
   * @param matchField 匹配的{@link FieldMeta}
   * @return 自己
   */
  public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  /**
   * 如果object的property为null时，返回怎样的字符串的设置
   *
   * @param nullString 字符串
   * @return 自己
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
  public String getPropertyString(T object, FieldMeta fieldMeta) {

    String fieldName = fieldMeta.getName();
    Object property = getProperty(object, fieldName);
    if (property != null) {
      return convertProperty(property);
    }
    return nullString;

  }

  protected Object getProperty(T object, String property) {

    BeanUtilsBean beanUtilsBean = beanUtilsBeanFactory.getInstance();
    Object propertyValue = null;

    try {
      propertyValue = beanUtilsBean.getPropertyUtils().getProperty(object, property);
    } catch (NestedNullException e) {
      logger.debug("Nested property is null", e);
    } catch (Exception e) {
      throw new Object2WorkbookComposeException("Sheet compose error", e);
    }
    return propertyValue;

  }

  /**
   * 将object的某个property转换成String
   *
   * @param property 要转换成String的某个property，不会为null
   * @return 字符串
   */
  protected abstract String convertProperty(Object property);

}
