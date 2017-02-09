package com.supwisdom.spreadsheet.mapper.w2o.validation.rule.buildin;

import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.SingleCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.DependencyRuleParam;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin.UniqueValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class UniqueValidatorFactory implements SingleCellValidatorFactory<UniqueValidator> {

  @Override
  public UniqueValidator create(DependencyRuleParam param, String matchField) {
    return new UniqueValidator()
        .matchField(matchField)
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
