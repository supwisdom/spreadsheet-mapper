package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Row;

import java.util.function.Function;

/**
 * 利用java8 lambda表达式的{@link ToStringConverter}
 *
 * @param <P> 要转换成{@link Row}的object的某个属性的类型
 */
public class LambdaToStringConverter<P> extends ToStringConverterTemplate<Object, LambdaToStringConverter> {

  private Function<P, String> function;

  /**
   * lambda表达式
   * property: 要导出为{@link Row}的object的某个属性的值
   *
   * @param function String function(T property)
   */
  public LambdaToStringConverter(Function<P, String> function) {
    this.function = function;

  }

  @Override
  protected String convertProperty(Object property) {
    return function.apply((P) property);
  }

}
