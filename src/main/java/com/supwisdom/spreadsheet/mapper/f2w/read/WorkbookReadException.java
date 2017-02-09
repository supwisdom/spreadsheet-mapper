package com.supwisdom.spreadsheet.mapper.f2w.read;

/**
 * Created by hanwen on 16/1/7.
 */
public class WorkbookReadException extends RuntimeException {

  public WorkbookReadException() {
    super();
  }

  public WorkbookReadException(String message) {
    super(message);
  }

  public WorkbookReadException(String message, Throwable cause) {
    super(message, cause);
  }

  public WorkbookReadException(Throwable cause) {
    super(cause);
  }

  public WorkbookReadException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
