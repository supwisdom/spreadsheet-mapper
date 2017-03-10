package com.supwisdom.spreadsheet.mapper.w2o;

/**
 * Created by hanwen on 5/3/16.
 */
public class Workbook2ObjectComposeException extends RuntimeException {

  public Workbook2ObjectComposeException() {
    super();
  }

  public Workbook2ObjectComposeException(String message) {
    super(message);
  }

  public Workbook2ObjectComposeException(String message, Throwable cause) {
    super(message, cause);
  }

  public Workbook2ObjectComposeException(Throwable cause) {
    super(cause);
  }

  protected Workbook2ObjectComposeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
