package com.supwisdom.spreadsheet.mapper.o2w.compose;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.compose.converter.FieldConverter;

import java.util.List;

/**
 * sheet compose helper, generated all cell type is string (include number, date etc.).
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface SheetComposeHelper<T> {

  /**
   * {@link FieldConverter} unique with {@link FieldConverter#getMatchField()} in one sheet (one to one)
   *
   * @param fieldConverter {@link FieldConverter}
   * @return {@link SheetComposeHelper}
   */
  SheetComposeHelper<T> addFieldConverter(FieldConverter<T> fieldConverter);

  /**
   * @param dataOfSheet list of data, may null
   * @param sheetMeta   {@link SheetMeta}
   * @return {@link Sheet}
   */
  Sheet compose(List<T> dataOfSheet, SheetMeta sheetMeta);
}
