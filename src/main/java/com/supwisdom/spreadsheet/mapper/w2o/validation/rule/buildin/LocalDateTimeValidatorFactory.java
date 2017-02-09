package com.supwisdom.spreadsheet.mapper.w2o.validation.rule.buildin;

import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.DependencyRuleParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.LocalDateTimeValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class LocalDateTimeValidatorFactory implements SingleCellValidatorFactory<LocalDateTimeValidator> {

  @Override
  public LocalDateTimeValidator create(DependencyRuleParam param, String matchField) {
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
