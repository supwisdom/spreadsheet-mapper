package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.workbook.buildin;


import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.workbook.WorkbookValidator;

/**
 * sheet size validator
 * <p>
 * Created by hanwen on 4/26/16.
 */
public class SheetSizeValidator implements WorkbookValidator {

  // default write workbook error message on sheet 1.
  private int messageOnSheet = 1;

  private int size;

  private String errorMessage;

  public SheetSizeValidator size(int size) {
    this.size = size;
    return this;
  }

  public SheetSizeValidator errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public SheetSizeValidator messageOnSheet(int messageOnSheet) {
    this.messageOnSheet = messageOnSheet;
    return this;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public boolean valid(Workbook workbook, WorkbookMeta workbookMeta) {
    return workbook.sizeOfSheets() == size;
  }

  @Override
  public Integer getMessageOnSheet() {
    return messageOnSheet;
  }
}
