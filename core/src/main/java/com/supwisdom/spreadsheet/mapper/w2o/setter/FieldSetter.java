package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 将{@link Row}转换为object时，对object的某个property设置值的工具
 *
 * @param <T> 将{@link Row}转换为object的类型
 */
public interface FieldSetter<T> {

  /**
   * @return which field this setter matched
   * @see FieldMeta#getName()
   */
  String getMatchField();

  /**
   * setValue object field from cell value
   *
   * @param object    supplied object
   * @param cell      which cell value
   * @param fieldMeta {@link FieldMeta}
   */
  void setValue(T object, Cell cell, FieldMeta fieldMeta);

}
