package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.NumberValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class NumberValidatorFactory implements SingleCellValidatorFactory<NumberValidator> {

  private static final NumberValidatorFactory INSTANCE = new NumberValidatorFactory();

  private NumberValidatorFactory() {
    // singleton
  }

  public static NumberValidatorFactory getInstance() {
    return INSTANCE;
  }
  
  @Override
  public NumberValidator create(ValidatorFactoryParam param, String matchField) {
    return new NumberValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
