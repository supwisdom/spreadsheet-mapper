package com.supwisdom.spreadsheet.mapper.w2o.validation.builder.factory;

import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.DigitsValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.ValidatorFactoryParam;

/**
 * Created by hanwen on 2017/1/22.
 */
public class DigitsValidatorFactory implements SingleCellValidatorFactory<DigitsValidator> {

  private static final DigitsValidatorFactory INSTANCE = new DigitsValidatorFactory();

  private DigitsValidatorFactory() {
    // singleton
  }

  public static DigitsValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public DigitsValidator create(ValidatorFactoryParam param, String matchField) {
    return new DigitsValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
