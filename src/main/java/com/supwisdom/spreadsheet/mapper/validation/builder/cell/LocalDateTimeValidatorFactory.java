package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.LocalDateTimeValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class LocalDateTimeValidatorFactory implements CellValidatorFactory<LocalDateTimeValidator> {

  private static final LocalDateTimeValidatorFactory INSTANCE = new LocalDateTimeValidatorFactory();

  private LocalDateTimeValidatorFactory() {
    // singleton
  }

  public static LocalDateTimeValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public LocalDateTimeValidator create(CellBuildUnitParam param) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("the local date time validator additional param not satisfied, need [String]");
    }

    return new LocalDateTimeValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .pattern((String) additionalParam);
  }
}
