package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

/**
 * Object工厂
 * <p>
 * 在将{@link Sheet}里的每个{@link Row}转换成Object的时候，第一步就是通过Object工厂获得一个Object。
 * 然后使用每个{@link Cell}里的值来填充Object的property。
 * </p>
 *
 * @param <T> Object类型
 */
public interface ObjectFactory<T> {

  /**
   * 返回一个Object
   *
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   * @return 返回一个Object
   */
  T create(Row row, SheetMeta sheetMeta);

}
