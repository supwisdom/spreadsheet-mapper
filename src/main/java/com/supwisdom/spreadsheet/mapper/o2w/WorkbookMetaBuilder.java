package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

/**
 * workbook meta builder
 * <p>
 * Created by hanwen on 2017/1/19.
 */
public interface WorkbookMetaBuilder {

  /**
   * get a custom build workbook meta
   *
   * @return {@link WorkbookMeta}
   */
  WorkbookMeta toWorkbookMeta();
}
