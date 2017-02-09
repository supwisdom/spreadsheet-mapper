package com.supwisdom.spreadsheet.mapper.w2f;

/**
 * Created by hanwen on 16/1/7.
 */
public class WorkbookWriteException extends RuntimeException {

  public WorkbookWriteException() {
    super();
  }

  public WorkbookWriteException(String message) {
    super(message);
  }

  public WorkbookWriteException(String message, Throwable cause) {
    super(message, cause);
  }

  public WorkbookWriteException(Throwable cause) {
    super(cause);
  }

  protected WorkbookWriteException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
