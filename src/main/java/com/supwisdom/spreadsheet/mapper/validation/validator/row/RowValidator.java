package com.supwisdom.spreadsheet.mapper.validation.validator.row;


import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.core.Row;

import java.util.Set;

/**
 * row validator, after sheet validators.
 * <p>
 * Created by hanwen on 4/26/16.
 */
public interface RowValidator {

  /**
   * the error message will be collected when validator failure if error message is not blank
   *
   * @return valid error message
   */
  String getErrorMessage();

  /**
   * valid supplied row
   *
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   * @return true if pass
   */
  boolean valid(Row row, SheetMeta sheetMeta);

  /**
   * @return error message on which fields
   */
  Set<String> getMessageOnFields();
}
