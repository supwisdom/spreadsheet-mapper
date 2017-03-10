package com.supwisdom.spreadsheet.mapper.model.core;

import java.io.Serializable;
import java.util.List;

/**
 * sheet
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface Sheet extends Serializable {

  /**
   * get sheet index
   *
   * @return 1-based
   */
  int getIndex();

  /**
   * @return name of sheet
   */
  String getName();

  /**
   * @return rows of this sheet ordered by row index
   */
  List<Row> getRows();

  /**
   * @return rows size of this sheet
   */
  int sizeOfRows();

  /**
   * get row by row index
   *
   * @param rowIndex 1-based
   * @return row
   */
  Row getRow(int rowIndex);

  /**
   * add row
   *
   * @param row row
   * @return true if success
   */
  boolean addRow(Row row);

  /**
   * @return first row
   */
  Row getFirstRow();

  /**
   * @return the workbook of this
   */
  Workbook getWorkbook();
}
