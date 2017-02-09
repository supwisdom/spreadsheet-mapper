package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.WorkbookBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import org.apache.commons.collections.CollectionUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/4.
 */
public class DefaultWorkbookComposer implements WorkbookComposer {

  private List<SheetComposer> sheetComposers = new ArrayList<>();

  @Override
  public WorkbookComposer addSheetComposeHelper(SheetComposer sheetComposer) {
    if (sheetComposer == null) {
      throw new IllegalArgumentException("sheet compose helper can not be null");
    }

    sheetComposers.add(sheetComposer);
    return this;
  }

  @Override
  public Workbook compose(List<List> dataOfSheets, WorkbookMeta workbookMeta) {
    int sizeOfData = CollectionUtils.size(dataOfSheets);
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfHelper = sheetComposers.size();

    if (sizeOfData != sizeOfSheetMetas) {
      throw new WorkbookComposeException("data's size[" + sizeOfData + "] not equals workbook meta's sheet meta size[" + sizeOfSheetMetas + "]");
    }
    if (sizeOfHelper != sizeOfData) {
      throw new WorkbookComposeException("data's size[" + sizeOfData + "] not equals sheet compose helper size[" + sizeOfHelper + "]");
    }

    Workbook workbook = new WorkbookBean();

    for (int i = 1; i <= sizeOfData; i++) {

      SheetComposer sheetComposer = sheetComposers.get(i - 1);
      List data = dataOfSheets.get(i - 1);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(i);

      //noinspection unchecked
      workbook.addSheet(sheetComposer.compose(data, sheetMeta));
    }

    return workbook;
  }
}
