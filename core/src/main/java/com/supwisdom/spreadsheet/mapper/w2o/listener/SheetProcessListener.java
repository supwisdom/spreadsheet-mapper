package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

import java.util.List;

/**
 * 在将{@link Sheet}转换成List&lt;Object&gt;时的监听器
 *
 * @param <T> Object的类型
 */
public interface SheetProcessListener<T> {

  /**
   * 在Sheet开始转换之前
   *
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   */
  void before(Sheet sheet, SheetMeta sheetMeta);

  /**
   * 在Sheet转换完成之后
   *
   * @param objects   已经转换完毕的object列表
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   */
  void after(List<T> objects, Sheet sheet, SheetMeta sheetMeta);
}
