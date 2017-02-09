package com.supwisdom.spreadsheet.mapper.w2o.validation.rule.buildin;

import com.supwisdom.spreadsheet.mapper.w2o.param.NumberScaleRangeParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.DependencyRuleParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.NumberScaleRangeValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class NumberScaleRangeValidatorFactory implements SingleCellValidatorFactory<NumberScaleRangeValidator> {

  private static final NumberScaleRangeValidatorFactory INSTANCE = new NumberScaleRangeValidatorFactory();

  private NumberScaleRangeValidatorFactory() {
    // singleton
  }

  public static NumberScaleRangeValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public NumberScaleRangeValidator create(DependencyRuleParam param, String matchField) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof NumberScaleRangeParam)) {
      throw new IllegalArgumentException("the number scale range validator additional param not satisfied, need [NumberScaleRangeParam]");
    }

    return new NumberScaleRangeValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .param((NumberScaleRangeParam) additionalParam);
  }
}
