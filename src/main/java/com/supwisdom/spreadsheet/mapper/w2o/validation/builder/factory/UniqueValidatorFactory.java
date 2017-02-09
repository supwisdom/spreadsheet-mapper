package com.supwisdom.spreadsheet.mapper.w2o.validation.builder.factory;

import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.ValidatorFactoryParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.UniqueValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class UniqueValidatorFactory implements SingleCellValidatorFactory<UniqueValidator> {

  private static final UniqueValidatorFactory INSTANCE = new UniqueValidatorFactory();

  private UniqueValidatorFactory() {
    // singleton
  }

  public static UniqueValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public UniqueValidator create(ValidatorFactoryParam param, String matchField) {
    return new UniqueValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
