package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认的{@link Workbook2ObjectComposer}
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

  /**
   * 执行顺序是挨个转换Workbook中的Sheet，并调用对应的Sheet2ObjectComposer
   *
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return 每个Sheet的转换结果
   */
  @Override
  public List<List> compose(Workbook workbook, WorkbookMeta workbookMeta) {
    int sizeOfSheets = workbook.sizeOfSheets();
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfComposer = sheet2ObjectComposers.size();

    if (sizeOfSheets != sizeOfSheetMetas) {
      throw new Workbook2ObjectComposeException(
          "Workbook has " + sizeOfSheets + " Sheet(s) but WorkBookMeta has " + sizeOfSheetMetas + " SheetMeta(s)");
    }
    if (sizeOfSheets != sizeOfComposer) {
      throw new Workbook2ObjectComposeException(
          "Workbook has " + sizeOfSheets + " Sheet(s) but there are " + sizeOfComposer
              + " Sheet2ObjectComposer(s) provided");
    }

    List<List> objectLists = new ArrayList<>();

    for (int sheetIndex = 1; sheetIndex <= sizeOfSheets; sheetIndex++) {

      Sheet2ObjectComposer sheet2ObjectComposer = sheet2ObjectComposers.get(sheetIndex - 1);
      Sheet sheet = workbook.getSheet(sheetIndex);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(sheetIndex);

      objectLists.add(sheet2ObjectComposer.compose(sheet, sheetMeta));
    }

    return objectLists;
  }

}
