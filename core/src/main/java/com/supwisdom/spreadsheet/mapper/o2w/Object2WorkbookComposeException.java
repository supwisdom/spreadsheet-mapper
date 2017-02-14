package com.supwisdom.spreadsheet.mapper.o2w;

/**
 * Created by hanwen on 2017/1/3.
 */
public class Object2WorkbookComposeException extends RuntimeException {

  public Object2WorkbookComposeException() {
    super();
  }

  public Object2WorkbookComposeException(String message) {
    super(message);
  }

  public Object2WorkbookComposeException(String message, Throwable cause) {
    super(message, cause);
  }

  public Object2WorkbookComposeException(Throwable cause) {
    super(cause);
  }

  protected Object2WorkbookComposeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
