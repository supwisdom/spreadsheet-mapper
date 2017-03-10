package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.NumberValidator;

/**
 * {@link NumberValidator}工厂
 * Created by hanwen on 2017/1/22.
 */
public class NumberValidatorFactory implements CellValidatorFactory<NumberValidator> {

  private static final NumberValidatorFactory INSTANCE = new NumberValidatorFactory();

  private NumberValidatorFactory() {
    // singleton
  }

  public static NumberValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public NumberValidator create(CellBuildUnitParam param) {
    return new NumberValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
