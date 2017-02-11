package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.NumberScaleRangeValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class NumberScaleRangeValidatorFactory implements CellValidatorFactory<NumberScaleRangeValidator> {

  private static final NumberScaleRangeValidatorFactory INSTANCE = new NumberScaleRangeValidatorFactory();

  private NumberScaleRangeValidatorFactory() {
    // singleton
  }

  public static NumberScaleRangeValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public NumberScaleRangeValidator create(CellBuildUnitParam param) {

    NumberScaleRangeValidator validator = null;

    Object additionalParam = param.getAdditionalParam();
    if (!(additionalParam instanceof NumberScaleRangeParam)) {
      throw new IllegalArgumentException("the number scale range validator additional param not satisfied, need [NumberScaleRangeParam]");
    }

    validator = new NumberScaleRangeValidator(((NumberScaleRangeParam) additionalParam).getGte(), ((NumberScaleRangeParam) additionalParam).getLte());

    return validator
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));

  }
}
