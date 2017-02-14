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
public class DefaultObject2WorkbookComposer implements Object2WorkbookComposer {

  private List<Object2SheetComposer> object2SheetComposers = new ArrayList<>();

  @Override
  public Object2WorkbookComposer addObject2SheetComposer(Object2SheetComposer object2SheetComposer) {
    if (object2SheetComposer == null) {
      throw new IllegalArgumentException("sheet compose helper can not be null");
    }

    object2SheetComposers.add(object2SheetComposer);
    return this;
  }

  @Override
  public Workbook compose(List<List> dataOfSheets, WorkbookMeta workbookMeta) {
    int sizeOfData = CollectionUtils.size(dataOfSheets);
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfHelper = object2SheetComposers.size();

    if (sizeOfData != sizeOfSheetMetas) {
      throw new Object2WorkbookComposeException("data's size[" + sizeOfData + "] not equals workbook meta's sheet meta size[" + sizeOfSheetMetas + "]");
    }
    if (sizeOfHelper != sizeOfData) {
      throw new Object2WorkbookComposeException("data's size[" + sizeOfData + "] not equals sheet compose helper size[" + sizeOfHelper + "]");
    }

    Workbook workbook = new WorkbookBean();

    for (int i = 1; i <= sizeOfData; i++) {

      Object2SheetComposer object2SheetComposer = object2SheetComposers.get(i - 1);
      List data = dataOfSheets.get(i - 1);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(i);

      //noinspection unchecked
      workbook.addSheet(object2SheetComposer.compose(data, sheetMeta));
    }

    return workbook;
  }
}
