package com.supwisdom.spreadsheet.mapper.w2o.validation.rule.buildin;

import com.supwisdom.spreadsheet.mapper.w2o.param.BooleanParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.DependencyRuleParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.BooleanValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class BooleanValidatorFactory implements SingleCellValidatorFactory<BooleanValidator> {

  private static final BooleanValidatorFactory INSTANCE = new BooleanValidatorFactory();

  private BooleanValidatorFactory() {
    // singleton
  }

  public static BooleanValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public BooleanValidator create(DependencyRuleParam param, String matchField) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof BooleanParam)) {
      throw new IllegalArgumentException("the boolean validator additional param not satisfied, need [BooleanParam]");
    }

    return new BooleanValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .param((BooleanParam) additionalParam);
  }

}
