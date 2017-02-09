package com.supwisdom.spreadsheet.mapper.w2o.validation.builder.factory;

import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.RequireValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.ValidatorFactoryParam;

/**
 * Created by hanwen on 2017/1/22.
 */
public class RequireValidatorFactory implements SingleCellValidatorFactory<RequireValidator> {

  private static final RequireValidatorFactory INSTANCE = new RequireValidatorFactory();

  private RequireValidatorFactory() {
    // singleton
  }

  public static RequireValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public RequireValidator create(ValidatorFactoryParam param, String matchField) {
    return new RequireValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
