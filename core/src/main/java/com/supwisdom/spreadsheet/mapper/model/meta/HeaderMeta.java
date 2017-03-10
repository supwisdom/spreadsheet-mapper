package com.supwisdom.spreadsheet.mapper.model.meta;

import java.io.Serializable;

/**
 * 表头元信息，包括
 * <ol>
 * <li>第几行</li>
 * <li>内容</li>
 * </ol>
 * Created by hanwen on 2016/12/29.
 */
public interface HeaderMeta extends Serializable, Comparable<HeaderMeta> {

  /**
   * @return 第几行，1-based
   */
  int getRowIndex();

  /**
   * @return 表头内容
   */
  String getValue();

  /**
   * @return 所属的 {@link FieldMeta}
   */
  FieldMeta getFieldMeta();
}
