package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.LambdaCellValidator;

/**
 * 用bifunction参数执行校验逻辑的validator factory
 * Created by hanwen on 2017/1/23.
 */
public class LambdaCellValidatorFactory implements CellValidatorFactory<LambdaCellValidator> {

  private static final LambdaCellValidatorFactory INSTANCE = new LambdaCellValidatorFactory();

  private LambdaCellValidatorFactory() {
    // singleton
  }

  public static LambdaCellValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public LambdaCellValidator create(CellBuildUnitParam param) {

    LambdaCellValidator validator = null;
    Object additionalParam = param.getAdditionalParam();
    if (additionalParam instanceof LambdaCellValidatorParam) {
      validator = new LambdaCellValidator(((LambdaCellValidatorParam) additionalParam).getLambda());
    }

    validator
        .matchField(param.getMatchField())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup());

    return validator;
  }

}
