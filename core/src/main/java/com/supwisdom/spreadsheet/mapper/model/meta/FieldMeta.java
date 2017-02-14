package com.supwisdom.spreadsheet.mapper.model.meta;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * field meta include:
 * 1. field name (like: object's field1, field2, etc.)
 *    this is importance, is determined all cell value of the same column index reflect which field of object
 * 2. field at column index
 * 3. list of header meta of this field (see {@link HeaderMeta})
 * </pre>
 * Created by hanwen on 2016/12/30.
 */
public interface FieldMeta extends Serializable, Comparable<FieldMeta> {

  /**
   * @return field name
   */
  String getName();

  /**
   * which column
   *
   * @return 1-based
   */
  int getColumnIndex();

  /**
   * @return list of header meta ordered by header meta row index
   */
  List<HeaderMeta> getHeaderMetas();

  /**
   * get header at row index
   *
   * @param rowIndex 1-based
   * @return header meta
   */
  HeaderMeta getHeaderMeta(int rowIndex);

  /**
   * remove header at row index
   *
   * @param rowIndex 1-based
   */
  void removeHeaderMeta(int rowIndex);

  /**
   * add header meta
   *
   * @param headerMeta header meta
   */
  void addHeaderMeta(HeaderMeta headerMeta);

  /**
   * @return the sheet meta of this
   */
  SheetMeta getSheetMeta();
}
