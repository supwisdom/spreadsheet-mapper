package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.RequireValidator;

/**
 * {@link RequireValidator}工厂
 * Created by hanwen on 2017/1/22.
 */
public class RequireValidatorFactory implements CellValidatorFactory<RequireValidator> {

  private static final RequireValidatorFactory INSTANCE = new RequireValidatorFactory();

  private RequireValidatorFactory() {
    // singleton
  }

  public static RequireValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public RequireValidator create(CellBuildUnitParam param) {
    return new RequireValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
