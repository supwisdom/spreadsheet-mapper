package com.supwisdom.spreadsheet.mapper.validation.engine;

/**
 * Created by qianjia on 2017/2/13.
 */
public class CellGroupValidationEngineException extends RuntimeException {
  public CellGroupValidationEngineException() {
    super();
  }

  public CellGroupValidationEngineException(String message) {
    super(message);
  }

  public CellGroupValidationEngineException(String message, Throwable cause) {
    super(message, cause);
  }

  public CellGroupValidationEngineException(Throwable cause) {
    super(cause);
  }

  protected CellGroupValidationEngineException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
