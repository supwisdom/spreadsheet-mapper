package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.LocalDateValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class LocalDateValidatorFactory implements CellValidatorFactory<LocalDateValidator> {

  private static final LocalDateValidatorFactory INSTANCE = new LocalDateValidatorFactory();

  private LocalDateValidatorFactory() {
    // singleton
  }

  public static LocalDateValidatorFactory getInstance() {
    return INSTANCE;
  }
  
  @Override
  public LocalDateValidator create(CellBuildUnitParam param) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("the local date validator additional param not satisfied, need [String]");
    }

    return new LocalDateValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .pattern((String) additionalParam);
  }
}
