package com.supwisdom.spreadsheet.mapper.validation;

/**
 * 工作簿校验工作执行过程中发生的异常
 * Created by hanwen on 2016/12/28.
 */
public class WorkbookValidateException extends RuntimeException {

  public WorkbookValidateException() {
    super();
  }

  public WorkbookValidateException(String message) {
    super(message);
  }

  public WorkbookValidateException(String message, Throwable cause) {
    super(message, cause);
  }

  public WorkbookValidateException(Throwable cause) {
    super(cause);
  }

  protected WorkbookValidateException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
