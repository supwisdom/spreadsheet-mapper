package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * listener of cell value to field processor
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public interface CellProcessListener<T> {

  /**
   * before object value setValue
   *
   * @param object    value not setValue
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   */
  void before(T object, Cell cell, FieldMeta fieldMeta);

  /**
   * after object value setValue
   *
   * @param object    value setValue but same object
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   */
  void after(T object, Cell cell, FieldMeta fieldMeta);
}
