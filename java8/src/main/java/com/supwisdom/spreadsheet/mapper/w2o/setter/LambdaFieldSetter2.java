package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.function.TriConsumer;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.omg.CORBA.Object;

/**
 * 使用java8 lambda表达式的object property设置器
 *
 * @param <T> 被设置property值的object的类型
 */
public class LambdaFieldSetter2<T> extends FieldSetterTemplate<T, LambdaFieldSetter2> {

  private TriConsumer<T, Cell, FieldMeta> lambda;

  public LambdaFieldSetter2() {
  }

  public LambdaFieldSetter2(TriConsumer<T, Cell, FieldMeta> lambda) {
    lambda(lambda);
  }

  public LambdaFieldSetter2<T> lambda(TriConsumer<T, Cell, FieldMeta> lambda) {
    this.lambda = lambda;
    return this;
  }

  @Override
  public void setValue(T object, Cell cell, FieldMeta fieldMeta) {

    lambda.accept(object, cell, fieldMeta);

  }

  @Override
  protected Object convertProperty(String cellValue) {
    // do nothing
    return null;
  }

}

