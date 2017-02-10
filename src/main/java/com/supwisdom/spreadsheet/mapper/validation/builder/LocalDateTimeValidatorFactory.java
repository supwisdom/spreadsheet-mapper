package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.LocalDateTimeValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class LocalDateTimeValidatorFactory implements SingleCellValidatorFactory<LocalDateTimeValidator> {

  private static final LocalDateTimeValidatorFactory INSTANCE = new LocalDateTimeValidatorFactory();

  private LocalDateTimeValidatorFactory() {
    // singleton
  }

  public static LocalDateTimeValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public LocalDateTimeValidator create(ValidatorFactoryParam param, String matchField) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("the local date time validator additional param not satisfied, need [String]");
    }

    return new LocalDateTimeValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .pattern((String) additionalParam);
  }
}
