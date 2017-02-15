package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.LambdaUnionCellValidator;

/**
 * 用bifunction参数执行校验逻辑的validator factory
 * Created by hanwen on 2017/1/23.
 */
public class LambdaUnionCellValidatorFactory implements UnionCellValidatorFactory<LambdaUnionCellValidator> {

  private static final LambdaUnionCellValidatorFactory INSTANCE = new LambdaUnionCellValidatorFactory();

  private LambdaUnionCellValidatorFactory() {
    // singleton
  }

  public static LambdaUnionCellValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public LambdaUnionCellValidator create(UnionCellBuildUnitParam param) {

    LambdaUnionCellValidator validator = null;
    Object additionalParam = param.getAdditionalParam();
    if (additionalParam instanceof LambdaUnionCellValidatorParam) {
      validator = new LambdaUnionCellValidator(((LambdaUnionCellValidatorParam) additionalParam).getLambda());
    }

    validator
        .matchFields(param.getMatchFields().toArray(new String[0]))
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup());

    return validator;
  }

}
