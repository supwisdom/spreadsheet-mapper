package com.supwisdom.spreadsheet.mapper.model.core;

import java.io.Serializable;
import java.util.List;

/**
 * workbook
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface Workbook extends Serializable {

  /**
   * @return sheets of this workbook ordered by sheet index
   */
  List<Sheet> getSheets();

  /**
   * @return sheets size of this workbook
   */
  int sizeOfSheets();

  /**
   * add sheet
   *
   * @param sheet sheet
   * @return true if success
   */
  boolean addSheet(Sheet sheet);

  /**
   * get sheet by index
   *
   * @param sheetIndex 1-based
   * @return sheet
   */
  Sheet getSheet(int sheetIndex);

  /**
   * @return first Sheet
   */
  Sheet getFirstSheet();
}
