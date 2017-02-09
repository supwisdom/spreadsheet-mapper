package com.supwisdom.spreadsheet.mapper.model.meta;

import java.io.Serializable;
import java.util.List;

/**
 * workbook meta include a list of sheet meta
 * <p>
 * Created by hanwen on 2017/1/18.
 */
public interface WorkbookMeta extends Serializable {

  /**
   * sheet meta size
   *
   * @return 1-based
   */
  int sizeOfSheetMetas();

  /**
   * @return sheet metas of this workbook meta order by sheet index
   */
  List<SheetMeta> getSheetMetas();

  /**
   * @return first sheet meta
   */
  SheetMeta getFirstSheetMeta();

  /**
   * get sheet meta by sheet index
   *
   * @param sheetIndex 1-based
   * @return sheet meta
   */
  SheetMeta getSheetMeta(int sheetIndex);

  /**
   * add sheet meta
   *
   * @param sheetMeta sheet meta
   * @return true if success
   */
  boolean addSheetMeta(SheetMeta sheetMeta);
}
