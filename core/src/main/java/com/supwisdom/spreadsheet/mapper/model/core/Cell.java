package com.supwisdom.spreadsheet.mapper.model.core;

import org.apache.poi.ss.usermodel.CellType;

import java.io.Serializable;

/**
 * Spreadsheet的单元格
 * Created by hanwen on 15-12-16.
 */
public interface Cell extends Serializable {

  /**
   * @return 单元格里的值
   */
  String getValue();

  /**
   * @return 第几列，1-based
   */
  int getIndex();

  /**
   * @return 所属的 {@link Row}
   */
  Row getRow();

  /**
   * @return 列的数值属性
   */
  CellType getCellType();
}
