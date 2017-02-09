package com.supwisdom.spreadsheet.mapper.w2o.compose.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * listener of cell value to field processor
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public interface CellProcessListener<T> {

  /**
   * before object value set
   *
   * @param object    value not set
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   */
  void before(T object, Cell cell, FieldMeta fieldMeta);

  /**
   * after object value set
   *
   * @param object    value set but same object
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   */
  void after(T object, Cell cell, FieldMeta fieldMeta);
}
