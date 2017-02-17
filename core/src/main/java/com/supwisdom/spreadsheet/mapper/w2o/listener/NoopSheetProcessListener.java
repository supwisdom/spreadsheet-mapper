package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

import java.util.List;

/**
 * 什么都不做的{@link SheetProcessListener}
 * Created by hanwen on 2016/12/28.
 */
public final class NoopSheetProcessListener<T> implements SheetProcessListener<T> {

  @Override
  public void before(Sheet sheet, SheetMeta sheetMeta) {
    // nothing
  }

  @Override
  public void after(List<T> objects, Sheet sheet, SheetMeta sheetMeta) {
    // nothing
  }
}
