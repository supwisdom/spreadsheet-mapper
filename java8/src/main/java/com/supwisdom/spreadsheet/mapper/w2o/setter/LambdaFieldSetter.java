
package com.supwisdom.spreadsheet.mapper.w2o.setter;

import java.util.function.Function;

/**
 * 使用java8 lambda表达式的object property设置器
 *
 * @param <P> 将String转换成property值的类型
 */
public class LambdaFieldSetter<P> extends FieldSetterTemplate<Object, LambdaFieldSetter> {

  private Function<String, P> lambda;

  public LambdaFieldSetter lambda(Function<String, P> lambda) {
    this.lambda = lambda;
    return this;
  }

  @Override
  protected P convertProperty(String cellValue) {
    return lambda.apply(cellValue);
  }

}
