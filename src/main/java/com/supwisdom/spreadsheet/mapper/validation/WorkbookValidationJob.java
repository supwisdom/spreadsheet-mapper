package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.validation.validator.workbook.WorkbookValidator;

import java.util.List;

/**
 * workbook validation job
 * <p>
 * Created by hanwen on 2017/1/4.
 */
public interface WorkbookValidationJob {

  /**
   * @param workbookValidator {@link WorkbookValidator}
   * @return {@link WorkbookValidationJob}
   */
  WorkbookValidationJob addWorkbookValidator(WorkbookValidator workbookValidator);

  /**
   * the sequence of the sheet validation job add is the helper used to validate workbook's sheets sequence.
   *
   * @param sheetValidationJob {@link SheetValidationJob}
   * @return {@link WorkbookValidationJob}
   */
  WorkbookValidationJob addSheetValidationJob(SheetValidationJob sheetValidationJob);

  /**
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return true if pass all
   * @see SheetValidationJob#validate(Sheet, SheetMeta)
   */
  boolean validate(Workbook workbook, WorkbookMeta workbookMeta);

  /**
   * @return error messages
   * @see SheetValidationJob#getErrorMessages()
   */
  List<Message> getErrorMessages();
}
