package com.supwisdom.spreadsheet.mapper.validation.validator.workbook;


import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

/**
 * workbook template validator
 * <p>
 * Created by hanwen on 4/26/16.
 */
public interface WorkbookValidator {

  /**
   * the error message will be collected when validator failure if error message is not blank
   *
   * @return valid error message
   */
  String getErrorMessage();

  /**
   * valid supplied workbook
   *
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return true if pass
   */
  boolean valid(Workbook workbook, WorkbookMeta workbookMeta);

  /**
   * @return message on which sheet
   */
  Integer getMessageOnSheet();
}
