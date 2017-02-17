package com.supwisdom.spreadsheet.mapper.validation.validator.workbook;

/**
 * 工作簿校验器模板
 * Created by qianjia on 2017/2/17.
 */
public abstract class WorkbookValidatorTemplate<V extends WorkbookValidatorTemplate<V>> implements WorkbookValidator {

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
