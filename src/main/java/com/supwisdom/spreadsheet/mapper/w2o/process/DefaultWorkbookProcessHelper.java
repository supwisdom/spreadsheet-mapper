package com.supwisdom.spreadsheet.mapper.w2o.process;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/4.
 */
public class DefaultWorkbookProcessHelper implements WorkbookProcessHelper {

  private List<SheetProcessHelper> sheetProcessHelpers = new ArrayList<>();

  @Override
  public WorkbookProcessHelper addSheetProcessHelper(SheetProcessHelper sheetProcessHelper) {
    if (sheetProcessHelper == null) {
      throw new IllegalArgumentException("sheet process helper can not be null");
    }

    sheetProcessHelpers.add(sheetProcessHelper);
    return this;
  }

  @Override
  public List<List> process(Workbook workbook, WorkbookMeta workbookMeta) {
    int sizeOfSheets = workbook.sizeOfSheets();
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfHelper = sheetProcessHelpers.size();

    if (sizeOfSheets != sizeOfSheetMetas) {
      throw new WorkbookProcessException("workbook's sheet size[" + sizeOfSheets + "] not equals workbook meta's sheet meta size[" + sizeOfSheetMetas + "]");
    }
    if (sizeOfSheets != sizeOfHelper) {
      throw new WorkbookProcessException("workbook's sheet size[" + sizeOfSheets + "] not equals sheet process helper size[" + sizeOfHelper + "]");
    }

    List<List> objects = new ArrayList<>();

    for (int i = 1; i <= sizeOfSheets; i++) {

      SheetProcessHelper sheetProcessHelper = sheetProcessHelpers.get(i - 1);
      Sheet sheet = workbook.getSheet(i);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(i);

      objects.add(sheetProcessHelper.process(sheet, sheetMeta));
    }

    return objects;
  }
}
