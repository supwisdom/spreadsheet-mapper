package com.supwisdom.spreadsheet.mapper.w2o.process;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.List;

/**
 * workbook process helper, simply adapter of sheet process helper
 * <p>
 * Created by hanwen on 2017/1/4.
 */
public interface WorkbookProcessHelper {

  /**
   * the sequence of the sheet process helper add is the helper used to process workbook's sheets sequence.
   *
   * @param sheetProcessHelper {@link SheetProcessHelper}
   * @return {@link WorkbookProcessHelper}
   */
  WorkbookProcessHelper addSheetProcessHelper(SheetProcessHelper sheetProcessHelper);

  /**
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return list of sheet list data
   * @see SheetProcessHelper#process(Sheet, SheetMeta)
   */
  List<List> process(Workbook workbook, WorkbookMeta workbookMeta);
}
