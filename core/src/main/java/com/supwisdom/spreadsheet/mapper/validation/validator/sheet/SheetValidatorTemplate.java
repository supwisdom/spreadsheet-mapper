package com.supwisdom.spreadsheet.mapper.validation.validator.sheet;

/**
 * 工作表校验器模板
 * Created by qianjia on 2017/2/17.
 */
public abstract class SheetValidatorTemplate<V extends SheetValidatorTemplate<V>> implements SheetValidator {

  private String errorMessage;

  final public V errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return (V) this;
  }

  @Override
  public String getErrorMessage() {
    return this.errorMessage;
  }

}
