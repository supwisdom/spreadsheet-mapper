package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

/**
 * 什么都不做的{@link RowProcessListener}
 * Created by hanwen on 2017/1/3.
 */
public final class NoopRowProcessListener<T> implements RowProcessListener<T> {

  @Override
  public void before(T object, Row row, SheetMeta sheetMeta) {
    // nothing
  }

  @Override
  public void after(T object, Row row, SheetMeta sheetMeta) {
    // nothing
  }
}
