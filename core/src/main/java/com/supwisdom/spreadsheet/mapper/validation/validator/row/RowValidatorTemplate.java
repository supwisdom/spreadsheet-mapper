package com.supwisdom.spreadsheet.mapper.validation.validator.row;

/**
 * 行校验器模板
 * Created by hanwen on 4/26/16.
 */
public abstract class RowValidatorTemplate<V extends RowValidatorTemplate<V>> implements RowValidator {

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
