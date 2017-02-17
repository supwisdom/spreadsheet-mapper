package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.function.BiFunction;

/**
 * 利用java8 lambda表达式的{@link PropertyStringifier}
 *
 * @param <T> 要转换成{@link com.supwisdom.spreadsheet.mapper.model.core.Row}的Object的类型
 */
public class LambdaPropertyStringifier2<T> extends PropertyStringifierTemplate<T, LocalDateTimePropertyStringifier> {

  private BiFunction<T, FieldMeta, String> biFunction;

  /**
   * lambda表达式
   * object：要导出为{@link Row}的object
   *
   * @param biFunction String function(T object, FieldMeta)
   */
  public LambdaPropertyStringifier2(BiFunction<T, FieldMeta, String> biFunction) {
    this.biFunction = biFunction;
  }

  @Override
  public String getPropertyString(T object, FieldMeta fieldMeta) {
    return biFunction.apply(object, fieldMeta);
  }

  @Override
  protected String convertProperty(Object property) {
    return null;
  }

}
