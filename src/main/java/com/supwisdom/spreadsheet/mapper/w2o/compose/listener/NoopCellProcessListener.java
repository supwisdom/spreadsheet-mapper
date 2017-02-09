package com.supwisdom.spreadsheet.mapper.w2o.compose.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * Created by hanwen on 2017/1/3.
 */
public final class NoopCellProcessListener<T> implements CellProcessListener<T> {

  @Override
  public void before(T object, Cell cell, FieldMeta fieldMeta) {
    // nothing
  }

  @Override
  public void after(T object, Cell cell, FieldMeta fieldMeta) {
    // nothing
  }
}
