package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.DateTimeFormatValidator;

public class DateTimeFormatValidatorFactory implements CellValidatorFactory<DateTimeFormatValidator> {

  private static final DateTimeFormatValidatorFactory INSTANCE = new DateTimeFormatValidatorFactory();

  private DateTimeFormatValidatorFactory() {
    // singleton
  }

  public static DateTimeFormatValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public DateTimeFormatValidator create(CellBuildUnitParam param) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("DateTimeFormatValidator additional param not satisfied, need [String]");
    }

    return new DateTimeFormatValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .pattern((String) additionalParam);
  }
}
