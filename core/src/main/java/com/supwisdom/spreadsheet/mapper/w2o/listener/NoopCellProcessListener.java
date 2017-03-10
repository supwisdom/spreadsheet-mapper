package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 什么都不做的{@link CellProcessListener}
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
