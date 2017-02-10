package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * multi cells validator, after row validators.
 * <p>
 * Created by hanwen on 2017/1/20.
 */
public interface MultiCellValidator extends Validator {

  /**
   * the error message will be collected when validator failure if error message is not blank
   *
   * @return validate error message
   */
  String getErrorMessage();

  /**
   * validate supplied cells, the sequence of cells and field metas is {@link #getMatchFields()} sequence
   *
   * @param cells      {@link Cell}
   * @param fieldMetas {@link FieldMeta}
   * @return true if pass
   */
  boolean validate(List<Cell> cells, List<FieldMeta> fieldMetas);

  /**
   * @return which fields this validator to validate
   * @see FieldMeta#getName()
   */
  LinkedHashSet<String> getMatchFields();
}
