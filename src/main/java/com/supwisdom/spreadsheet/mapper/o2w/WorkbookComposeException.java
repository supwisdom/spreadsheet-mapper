package com.supwisdom.spreadsheet.mapper.o2w;

/**
 * Created by hanwen on 2017/1/3.
 */
public class WorkbookComposeException extends RuntimeException {

  public WorkbookComposeException() {
    super();
  }

  public WorkbookComposeException(String message) {
    super(message);
  }

  public WorkbookComposeException(String message, Throwable cause) {
    super(message, cause);
  }

  public WorkbookComposeException(Throwable cause) {
    super(cause);
  }

  protected WorkbookComposeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
