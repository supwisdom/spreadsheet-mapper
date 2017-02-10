package com.supwisdom.spreadsheet.mapper.w2o;

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
public interface Workbook2ObjectComposer {

  /**
   * the sequence of the sheet process helper add is the helper used to process workbook's sheets sequence.
   *
   * @param sheet2ObjectComposer {@link Sheet2ObjectComposer}
   * @return {@link Workbook2ObjectComposer}
   */
  Workbook2ObjectComposer addSheet2ObjectComposer(Sheet2ObjectComposer sheet2ObjectComposer);

  /**
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return list of sheet list data
   * @see Sheet2ObjectComposer#compose(Sheet, SheetMeta)
   */
  List<List> compose(Workbook workbook, WorkbookMeta workbookMeta);
}
