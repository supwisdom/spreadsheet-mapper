
package com.supwisdom.spreadsheet.mapper.w2o.setter;

import java.util.function.Function;

/**
 * 使用java8 lambda表达式的object property设置器
 *
 * @param <P> 将String转换成property值的类型
 */
public class LambdaPropertySetter<P> extends PropertySetterTemplate<Object, LambdaPropertySetter> {

  private Function<String, P> lambda;

  public LambdaPropertySetter(Function<String, P> lambda) {
    this.lambda = lambda;
  }
  @Override
  protected P convertToProperty(String cellValue) {
    return lambda.apply(cellValue);
  }

}
