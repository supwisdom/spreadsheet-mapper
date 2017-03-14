package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.bean.BeanHelper;
import com.supwisdom.spreadsheet.mapper.bean.BeanHelperBean;
import com.supwisdom.spreadsheet.mapper.bean.BeanPropertyWriteException;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposeException;
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

  protected BeanHelper beanHelper = new BeanHelperBean();

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

    String propertyPath = fieldMeta.getName();
    String propertyValue = cell.getValue();

    try {
      if (StringUtils.isNotBlank(propertyValue)) {
        beanHelper.setProperty(object, propertyPath, convertToProperty(propertyValue));
      } else {
        beanHelper.setProperty(object, propertyPath, null);
      }

    } catch (BeanPropertyWriteException e) {
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
