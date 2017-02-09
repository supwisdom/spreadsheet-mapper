package com.supwisdom.spreadsheet.mapper.model.meta;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * sheet meta include:
 * list of field meta (see {@link FieldMeta})
 * data start at which row ({@link #getDataStartRowIndex()})
 * sheet name ({@link Sheet#getName()})
 * sheet index ({@link Sheet#getIndex()})
 * </pre>
 * Created by hanwen on 2016/12/27.
 */
public interface SheetMeta extends Serializable {

  /**
   * @return sheet index
   */
  int getSheetIndex();

  /**
   * @return sheet name
   */
  String getSheetName();

  /**
   * <pre>
   * data row start at index
   * data start at which row (must be after header rows if has)
   * </pre>
   *
   * @return 1-based
   */
  int getDataStartRowIndex();

  /**
   * @return list of field meta ordered by field meta column index
   */
  List<FieldMeta> getFieldMetas();

  /**
   * @param fieldName field name
   * @return field meta of field name
   */
  FieldMeta getFieldMeta(String fieldName);

  /**
   * remove field meta of field name
   *
   * @param fieldName field name
   */
  void removeFieldMeta(String fieldName);

  /**
   * add field meta
   *
   * @param fieldMeta field meta
   */
  void addFieldMeta(FieldMeta fieldMeta);

  /**
   * @return the workbook meta of this
   */
  WorkbookMeta getWorkbookMeta();
}
