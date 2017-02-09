package com.supwisdom.spreadsheet.mapper.w2o.process;

/**
 * Created by hanwen on 5/3/16.
 */
public class WorkbookProcessException extends RuntimeException {

  public WorkbookProcessException() {
    super();
  }

  public WorkbookProcessException(String message) {
    super(message);
  }

  public WorkbookProcessException(String message, Throwable cause) {
    super(message, cause);
  }

  public WorkbookProcessException(Throwable cause) {
    super(cause);
  }

  protected WorkbookProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
