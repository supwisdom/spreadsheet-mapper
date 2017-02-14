package com.supwisdom.spreadsheet.mapper.function;

/**
 * Created by qianjia on 2017/2/14.
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {

  void accept(T t, U u, V v);

}
