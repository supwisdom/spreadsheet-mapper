package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Row;

import java.util.function.Function;

/**
 * 利用java8 lambda表达式的{@link PropertyStringifier}
 *
 * @param <P> 要转换成{@link Row}的object的某个property的类型
 */
public class LambdaPropertyStringifier<P> extends PropertyStringifierTemplate<Object, LambdaPropertyStringifier> {

  private Function<P, String> function;

  /**
   * lambda表达式
   * property: 要导出为{@link Row}的object的某个property的值
   *
   * @param function String function(T property)
   */
  public LambdaPropertyStringifier(Function<P, String> function) {
    this.function = function;

  }

  @Override
  protected String convertProperty(Object property) {
    return function.apply((P) property);
  }

}
