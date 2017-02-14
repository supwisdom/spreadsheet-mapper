package com.supwisdom.spreadsheet.mapper.w2o.setter;


import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * object field value setter
 * <p>
 * Created by hanwen on 15-12-16.
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
