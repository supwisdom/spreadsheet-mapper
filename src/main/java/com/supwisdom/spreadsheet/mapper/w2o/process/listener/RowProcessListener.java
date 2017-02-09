package com.supwisdom.spreadsheet.mapper.w2o.process.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

/**
 * listener of row to object processor
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public interface RowProcessListener<T> {

  /**
   * before object value set
   *
   * @param object    value not set
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   */
  void before(T object, Row row, SheetMeta sheetMeta);

  /**
   * after object value set
   *
   * @param object    value set but same object
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   */
  void after(T object, Row row, SheetMeta sheetMeta);
}
