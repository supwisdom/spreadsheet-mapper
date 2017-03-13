package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
import jodd.bean.BeanUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义{@link PropertySetter}的抽象类，只需实现{@link #convertToProperty(String)} 即可。
 * 需要注意的是，当{@link Cell#getValue()}是{@link StringUtils#isBlank(CharSequence)}的时候，会跳过执行{@link #convertToProperty(String)}。
 *
 * @param <T> 被设置property的Object的类型
 * @param <V> 自己的类型
 */
public abstract class PropertySetterTemplate<T, V extends PropertySetterTemplate> implements PropertySetter<T> {

  protected String matchField;

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

    if (StringUtils.isNotBlank(value)) {
      setProperty(object, field, convertToProperty(value));
    } else {
      setProperty(object, field, null);
    }

  }

  protected void setProperty(T object, String property, Object propertyValue) {

    try {
      BeanUtil.pojo.setProperty(object, property, propertyValue);
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
