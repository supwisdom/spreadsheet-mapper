package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 将{@link Row}转换为object时，对object的某个property设置值的工具。
 * 每个{@link PropertySetter}对应一个{@link FieldMeta}。
 *
 * @param <T> 被设置property的Object的类型
 */
public interface PropertySetter<T> {

  /**
   * @return 匹配 {@link FieldMeta#getName()}
   */
  String getMatchField();

  /**
   * 为Object设置property
   *
   * @param object    被设置property的Object
   * @param cell      匹配的{@link Cell}
   * @param fieldMeta {@link Cell}对应的{@link FieldMeta}
   */
  void setProperty(T object, Cell cell, FieldMeta fieldMeta);

}
