package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;

/**
 * <pre>
 * CellValidator, after workbook and sheet and row validators, if post validators failure, CellValidators will skip.
 * CellValidator will hit on each rows.
 * notice:
 * 1. each rows validate result is isolated.
 * 2. when validate one row the other rows validate result not influence this row validate.
 * 3. only hit on data rows {@link SheetMeta#getDataStartRowIndex()}.
 * 4. all validators hit sequence (if no dependency) is validator add to helper sequence.
 * 5. the same group validators hit sequence is validator add to helper sequence.
 * </pre>
 * Created by hanwen on 2016/12/26.
 */
public interface CellValidator extends Dependant {

  /**
   * 获得匹配的field
   *
   * @return
   */
  String getMatchField();

  /**
   * the error message will be collected when validator failure if error message is not blank
   *
   * @return validate error message
   */
  String getErrorMessage();

  /**
   * validate supplied cell
   *
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   * @return true if pass
   */
  boolean validate(Cell cell, FieldMeta fieldMeta);

}
