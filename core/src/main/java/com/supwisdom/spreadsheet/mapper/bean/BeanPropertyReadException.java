package com.supwisdom.spreadsheet.mapper.bean;

/**
 * 读Bean属性值时的异常
 * Created by qianjia on 2017/3/14.
 */
public class BeanPropertyReadException extends Exception {
  public BeanPropertyReadException() {
    super();
  }

  public BeanPropertyReadException(String message) {
    super(message);
  }

  public BeanPropertyReadException(String message, Throwable cause) {
    super(message, cause);
  }

  public BeanPropertyReadException(Throwable cause) {
    super(cause);
  }

  protected BeanPropertyReadException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
