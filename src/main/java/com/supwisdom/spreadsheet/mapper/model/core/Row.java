package com.supwisdom.spreadsheet.mapper.model.core;

import java.io.Serializable;
import java.util.List;

/**
 * row
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface Row extends Serializable {

  /**
   * @return row index 1-based
   */
  int getIndex();

  /**
   * @return cells of this row ordered by column index
   */
  List<Cell> getCells();

  /**
   * @return cells size of this row
   */
  int sizeOfCells();

  /**
   * get cell by column index
   *
   * @param columnIndex 1-based
   * @return cell
   */
  Cell getCell(int columnIndex);

  /**
   * add cell
   *
   * @param cell cell
   * @return true if success
   */
  boolean addCell(Cell cell);

  /**
   * @return first cell of this row
   */
  Cell getFirstCell();

  /**
   * @return the sheet of this
   */
  Sheet getSheet();

}
