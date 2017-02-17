package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.ValueScopeValidator;

/**
 * {@link ValueScopeValidator}工厂
 * Created by hanwen on 2017/1/22.
 */
public class ValueScopeValidatorFactory implements CellValidatorFactory<ValueScopeValidator> {

  private static final ValueScopeValidatorFactory INSTANCE = new ValueScopeValidatorFactory();

  private ValueScopeValidatorFactory() {
    // singleton
  }

  public static ValueScopeValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public ValueScopeValidator create(CellBuildUnitParam param) {

    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof ValueScopeParam)) {
      throw new IllegalArgumentException("the boolean validator additional param not satisfied, need [BooleanParam]");
    }

    return new ValueScopeValidator(((ValueScopeParam) additionalParam).getRestrictValues())
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));

  }

}
