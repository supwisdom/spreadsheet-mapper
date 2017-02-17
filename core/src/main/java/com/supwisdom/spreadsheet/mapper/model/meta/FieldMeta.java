package com.supwisdom.spreadsheet.mapper.model.meta;

import java.io.Serializable;
import java.util.List;

/**
 * Sheet中每列的元信息，包含以下信息：
 * <ol>
 * <li>列的名字</li>
 * <li>自己在Sheet中的第几列</li>
 * <li>表头元信息，如果表头有多行，那么就有多个</li>
 * </ol>
 * Created by hanwen on 2016/12/30.
 */
public interface FieldMeta extends Serializable, Comparable<FieldMeta> {

  /**
   * @return 名字
   */
  String getName();

  /**
   * @return 第几列，1-based
   */
  int getColumnIndex();

  /**
   * @return 表头元信息
   */
  List<HeaderMeta> getHeaderMetas();

  /**
   * 获得第几行的表头元信息
   *
   * @param rowIndex 第几行，1-based
   * @return 表头元
   */
  HeaderMeta getHeaderMeta(int rowIndex);

  /**
   * 移除第几行的表头元信息
   *
   * @param rowIndex 第几行，1-based
   */
  void removeHeaderMeta(int rowIndex);

  /**
   * 添加表头元信息
   *
   * @param headerMeta 表头元信息
   */
  void addHeaderMeta(HeaderMeta headerMeta);

  /**
   * @return 自己属于哪个 {@link SheetMeta}
   */
  SheetMeta getSheetMeta();
}
