package com.supwisdom.spreadsheet.mapper.model.core;

import java.io.Serializable;
import java.util.List;

/**
 * Spreadsheet的行
 * Created by hanwen on 15-12-16.
 */
public interface Row extends Serializable {

  /**
   * @return 第几行 1-based
   */
  int getIndex();

  /**
   * @return 自己拥有的 {@link Cell}
   */
  List<Cell> getCells();

  /**
   * @return 单元格数量
   */
  int sizeOfCells();

  /**
   * 根据第几列获得单元格
   *
   * @param columnIndex 第几列，1-based
   * @return 单元格
   */
  Cell getCell(int columnIndex);

  /**
   * 添加单元格
   *
   * @param cell 单元格
   * @return FIXME 似乎没有必要
   */
  boolean addCell(Cell cell);

  /**
   * FIXME 似乎没有必要
   *
   * @return first cell of this row
   */
  Cell getFirstCell();

  /**
   * @return 所属的 {@link Sheet}
   */
  Sheet getSheet();

}
