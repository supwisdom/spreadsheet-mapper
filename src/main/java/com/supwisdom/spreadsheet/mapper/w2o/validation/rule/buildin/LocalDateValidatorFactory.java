package com.supwisdom.spreadsheet.mapper.w2o.validation.rule.buildin;

import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.DependencyRuleParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.LocalDateValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class LocalDateValidatorFactory implements SingleCellValidatorFactory<LocalDateValidator> {

  private static final LocalDateValidatorFactory INSTANCE = new LocalDateValidatorFactory();

  private LocalDateValidatorFactory() {
    // singleton
  }

  public static LocalDateValidatorFactory getInstance() {
    return INSTANCE;
  }
  
  @Override
  public LocalDateValidator create(DependencyRuleParam param, String matchField) {
    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof String)) {
      throw new IllegalArgumentException("the local date validator additional param not satisfied, need [String]");
    }

    return new LocalDateValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .pattern((String) additionalParam);
  }
}
