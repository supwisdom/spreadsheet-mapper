package com.supwisdom.spreadsheet.mapper.w2o.validation.builder.factory;

import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.ValidatorFactoryParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.builder.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.RegexFormatValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class RegexFormatValidatorFactory implements SingleCellValidatorFactory<RegexFormatValidator> {

  private static final RegexFormatValidatorFactory INSTANCE = new RegexFormatValidatorFactory();

  private RegexFormatValidatorFactory() {
    // singleton
  }

  public static RegexFormatValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public RegexFormatValidator create(ValidatorFactoryParam param, String matchField) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("the regex format validator additional param not satisfied, need [String]");
    }

    return new RegexFormatValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .regex((String) additionalParam);
  }
}
