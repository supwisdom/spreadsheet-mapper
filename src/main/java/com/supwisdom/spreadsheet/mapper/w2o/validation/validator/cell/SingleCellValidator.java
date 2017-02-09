package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell;


import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * single cell validator, general(no dependency) after multi cells validators.
 * <p>
 * Created by hanwen on 15-12-15.
 */
public interface SingleCellValidator extends Validator {

  /**
   * the error message will be collected when validator failure if error message is not blank
   *
   * @return valid error message
   */
  String getErrorMessage();

  /**
   * valid supplied cell
   *
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   * @return true if pass
   */
  boolean valid(Cell cell, FieldMeta fieldMeta);

  /**
   * @return which field this validator to valid
   * @see FieldMeta#getName()
   */
  String getMatchField();
}
