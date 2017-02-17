package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.ObjectFactory;

/**
 * 在将{@link Row}转换成Object时的监听器
 *
 * @param <T> Object的类型
 */
public interface RowProcessListener<T> {

  /**
   * 在给Object设置值之前
   *
   * @param object    被设置值的Object，此时的Object是刚被{@link ObjectFactory}创建出来的
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   */
  void before(T object, Row row, SheetMeta sheetMeta);

  /**
   * 在给Object设置值之后
   *
   * @param object    被设置值的Object
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   */
  void after(T object, Row row, SheetMeta sheetMeta);
}
