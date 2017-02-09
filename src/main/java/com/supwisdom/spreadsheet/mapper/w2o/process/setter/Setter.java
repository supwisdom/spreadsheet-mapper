package com.supwisdom.spreadsheet.mapper.w2o.process.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * value setter
 * <p>
 * Created by hanwen on 2016/12/30.
 */
public interface Setter<T> {

  /**
   * set object field from cell value
   *
   * @param object    supplied object
   * @param cell      which cell value
   * @param fieldMeta {@link FieldMeta}
   */
  void set(T object, Cell cell, FieldMeta fieldMeta);
}
