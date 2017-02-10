package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.w2o.listener.CellProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.RowProcessListener;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.listener.SheetProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.setter.FieldSetter;

import java.util.List;

/**
 * sheet process helper
 * <p>
 * Created by hanwen on 2016/12/28.
 */
public interface Sheet2ObjectComposer<T> {

  /**
   * {@link FieldSetter} unique with {@link FieldSetter#getMatchField()} in one sheet (one to one)
   *
   * @param fieldSetter {@link FieldSetter}
   * @return {@link Sheet2ObjectComposer}
   * @see FieldSetter
   */
  Sheet2ObjectComposer<T> addFieldSetter(FieldSetter<T> fieldSetter);

  /**
   * @param objectFactory {@link ObjectFactory}
   * @return {@link Sheet2ObjectComposer}
   */
  Sheet2ObjectComposer<T> setObjectFactory(ObjectFactory<T> objectFactory);

  /**
   * @param sheetProcessListener {@link SheetProcessListener}
   * @return {@link Sheet2ObjectComposer}
   */
  Sheet2ObjectComposer<T> setSheetProcessorListener(SheetProcessListener<T> sheetProcessListener);

  /**
   * @param rowProcessListener {@link RowProcessListener}
   * @return {@link Sheet2ObjectComposer}
   */
  Sheet2ObjectComposer<T> setRowProcessorListener(RowProcessListener<T> rowProcessListener);

  /**
   * @param cellProcessListener {@link CellProcessListener}
   * @return {@link Sheet2ObjectComposer}
   */
  Sheet2ObjectComposer<T> setCellProcessorListener(CellProcessListener<T> cellProcessListener);

  /**
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   * @return list of data
   */
  List<T> compose(Sheet sheet, SheetMeta sheetMeta);
}
