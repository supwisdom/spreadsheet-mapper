package com.supwisdom.spreadsheet.mapper.w2o.process;

import com.supwisdom.spreadsheet.mapper.w2o.process.listener.CellProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.process.listener.RowProcessListener;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.process.listener.SheetProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.process.setter.FieldSetter;

import java.util.List;

/**
 * sheet process helper
 * <p>
 * Created by hanwen on 2016/12/28.
 */
public interface SheetProcessHelper<T> {

  /**
   * {@link FieldSetter} unique with {@link FieldSetter#getMatchField()} in one sheet (one to one)
   *
   * @param fieldSetter {@link FieldSetter}
   * @return {@link SheetProcessHelper}
   * @see FieldSetter
   */
  SheetProcessHelper<T> addFieldSetter(FieldSetter<T> fieldSetter);

  /**
   * @param objectFactory {@link ObjectFactory}
   * @return {@link SheetProcessHelper}
   */
  SheetProcessHelper<T> setObjectFactory(ObjectFactory<T> objectFactory);

  /**
   * @param sheetProcessListener {@link SheetProcessListener}
   * @return {@link SheetProcessHelper}
   */
  SheetProcessHelper<T> setSheetProcessorListener(SheetProcessListener<T> sheetProcessListener);

  /**
   * @param rowProcessListener {@link RowProcessListener}
   * @return {@link SheetProcessHelper}
   */
  SheetProcessHelper<T> setRowProcessorListener(RowProcessListener<T> rowProcessListener);

  /**
   * @param cellProcessListener {@link CellProcessListener}
   * @return {@link SheetProcessHelper}
   */
  SheetProcessHelper<T> setCellProcessorListener(CellProcessListener<T> cellProcessListener);

  /**
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   * @return list of data
   */
  List<T> process(Sheet sheet, SheetMeta sheetMeta);
}
