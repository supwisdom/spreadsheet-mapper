package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.List;

/**
 * workbook compose helper, simply adapter of sheet compose helper
 * <p>
 * Created by hanwen on 2017/1/4.
 */
public interface WorkbookComposer {

  /**
   * the sequence of the sheet compose helper add is the helper used to compose data's sequence.
   *
   * @param sheetComposer {@link SheetComposer}
   * @return {@link WorkbookComposer}
   */
  WorkbookComposer addSheetComposeHelper(SheetComposer sheetComposer);

  /**
   * @param dataOfSheets data of sheets
   * @param workbookMeta {@link WorkbookMeta}
   * @return {@link Workbook}
   * @see SheetComposer#compose(List, SheetMeta)
   */
  Workbook compose(List<List> dataOfSheets, WorkbookMeta workbookMeta);
}
