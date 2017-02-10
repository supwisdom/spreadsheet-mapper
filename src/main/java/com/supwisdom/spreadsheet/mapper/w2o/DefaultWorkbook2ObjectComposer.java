package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/4.
 */
public class DefaultWorkbook2ObjectComposer implements Workbook2ObjectComposer {

  private List<Sheet2ObjectComposer> sheet2ObjectComposers = new ArrayList<>();

  @Override
  public Workbook2ObjectComposer addSheet2ObjectComposer(Sheet2ObjectComposer sheet2ObjectComposer) {
    if (sheet2ObjectComposer == null) {
      throw new IllegalArgumentException("sheet process helper can not be null");
    }

    sheet2ObjectComposers.add(sheet2ObjectComposer);
    return this;
  }

  @Override
  public List<List> compose(Workbook workbook, WorkbookMeta workbookMeta) {
    int sizeOfSheets = workbook.sizeOfSheets();
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfHelper = sheet2ObjectComposers.size();

    if (sizeOfSheets != sizeOfSheetMetas) {
      throw new Workbook2ObjectComposeException("workbook's sheet size[" + sizeOfSheets + "] not equals workbook meta's sheet meta size[" + sizeOfSheetMetas + "]");
    }
    if (sizeOfSheets != sizeOfHelper) {
      throw new Workbook2ObjectComposeException("workbook's sheet size[" + sizeOfSheets + "] not equals sheet process helper size[" + sizeOfHelper + "]");
    }

    List<List> objects = new ArrayList<>();

    for (int i = 1; i <= sizeOfSheets; i++) {

      Sheet2ObjectComposer sheet2ObjectComposer = sheet2ObjectComposers.get(i - 1);
      Sheet sheet = workbook.getSheet(i);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(i);

      objects.add(sheet2ObjectComposer.compose(sheet, sheetMeta));
    }

    return objects;
  }
}
