package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

/**
 * workbook meta factory
 * <p>
 * Created by hanwen on 2017/1/19.
 */
public interface WorkbookMetaFactory {

  /**
   * create a workbook meta from supplied workbook
   *
   * @param workbook {@link Workbook}
   * @return {@link WorkbookMeta}
   */
  WorkbookMeta create(Workbook workbook);
}
