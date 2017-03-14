package com.supwisdom.spreadsheet.mapper.bean;

/**
 * 设置Bean属性值时的异常
 * Created by qianjia on 2017/3/14.
 */
public class BeanPropertyWriteException extends Exception {
  public BeanPropertyWriteException() {
    super();
  }

  public BeanPropertyWriteException(String message) {
    super(message);
  }

  public BeanPropertyWriteException(String message, Throwable cause) {
    super(message, cause);
  }

  public BeanPropertyWriteException(Throwable cause) {
    super(cause);
  }

  protected BeanPropertyWriteException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
