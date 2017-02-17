package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 在将{@link Cell}里的值设置到Object的property里时的监听器
 *
 * @param <T> Object的类型
 */
public interface CellProcessListener<T> {

  /**
   * 在设置property之前
   *
   * @param object    被设置值的Object
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   */
  void before(T object, Cell cell, FieldMeta fieldMeta);

  /**
   * 在设置property之后
   *
   * @param object    被设置值的Object
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   */
  void after(T object, Cell cell, FieldMeta fieldMeta);
}
