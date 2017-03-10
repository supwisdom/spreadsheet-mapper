package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.PropertyStringifier;

import java.util.List;

/**
 * sheet compose helper, generated all cell type is string (include number, date etc.).
 * Created by hanwen on 15-12-16.
 */
public interface Object2SheetComposer<T> {

  /**
   * {@link PropertyStringifier} unique with {@link PropertyStringifier#getMatchField()} in one sheet (one to one)
   *
   * @param propertyStringifier {@link PropertyStringifier}
   * @return {@link Object2SheetComposer}
   */
  Object2SheetComposer<T> addFieldConverter(PropertyStringifier propertyStringifier);

  /**
   * @param dataOfSheet list of data, may null
   * @param sheetMeta   {@link SheetMeta}
   * @return {@link Sheet}
   */
  Sheet compose(List<T> dataOfSheet, SheetMeta sheetMeta);
}
