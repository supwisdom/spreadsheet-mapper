package com.supwisdom.spreadsheet.mapper.w2o.compose;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

/**
 * object factory
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface ObjectFactory<T> {

  /**
   * initial one row present object to access cell value.
   *
   * @param row       which row's object
   * @param sheetMeta {@link SheetMeta}
   * @return initialized object
   */
  T create(Row row, SheetMeta sheetMeta);
}
