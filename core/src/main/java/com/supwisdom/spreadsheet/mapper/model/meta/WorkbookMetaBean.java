package com.supwisdom.spreadsheet.mapper.model.meta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/18.
 */
public class WorkbookMetaBean implements WorkbookMeta {

  private List<SheetMeta> sheetMetas = new ArrayList<>();

  @Override
  public int sizeOfSheetMetas() {
    return sheetMetas.size();
  }

  @Override
  public List<SheetMeta> getSheetMetas() {
    return sheetMetas;
  }

  @Override
  public SheetMeta getFirstSheetMeta() {
    return getSheetMeta(1);
  }

  @Override
  public SheetMeta getSheetMeta(int sheetIndex) {
    if (sheetIndex < 1 || sheetIndex > sizeOfSheetMetas()) {
      throw new IllegalArgumentException("sheet index index out of bounds");
    }
    if (sizeOfSheetMetas() == 0) {
      return null;
    }
    return sheetMetas.get(sheetIndex - 1);
  }

  @Override
  public boolean addSheetMeta(SheetMeta sheetMeta) {
    ((SheetMetaBean) sheetMeta).setWorkbookMeta(this);
    ((SheetMetaBean) sheetMeta).setSheetIndex(sizeOfSheetMetas() + 1);
    return sheetMetas.add(sheetMeta);
  }
}
