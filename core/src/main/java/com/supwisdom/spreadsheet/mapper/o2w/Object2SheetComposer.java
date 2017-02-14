package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.FieldConverter;

import java.util.List;

/**
 * sheet compose helper, generated all cell type is string (include number, date etc.).
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface Object2SheetComposer<T> {

  /**
   * {@link FieldConverter} unique with {@link FieldConverter#getMatchField()} in one sheet (one to one)
   *
   * @param fieldConverter {@link FieldConverter}
   * @return {@link Object2SheetComposer}
   */
  Object2SheetComposer<T> addFieldConverter(FieldConverter<T> fieldConverter);

  /**
   * @param dataOfSheet list of data, may null
   * @param sheetMeta   {@link SheetMeta}
   * @return {@link Sheet}
   */
  Sheet compose(List<T> dataOfSheet, SheetMeta sheetMeta);
}
