package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.RegexFormatValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class RegexFormatValidatorFactory implements CellValidatorFactory<RegexFormatValidator> {

  private static final RegexFormatValidatorFactory INSTANCE = new RegexFormatValidatorFactory();

  private RegexFormatValidatorFactory() {
    // singleton
  }

  public static RegexFormatValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public RegexFormatValidator create(CellBuildUnitParam param) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("the regex format validator additional param not satisfied, need [String]");
    }

    return new RegexFormatValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .regex((String) additionalParam);
  }
}
