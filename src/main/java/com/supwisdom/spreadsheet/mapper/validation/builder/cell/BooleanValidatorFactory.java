package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.BooleanValidator;

/**
 * Created by hanwen on 2017/1/22.
 */
public class BooleanValidatorFactory implements CellValidatorFactory<BooleanValidator> {

  private static final BooleanValidatorFactory INSTANCE = new BooleanValidatorFactory();

  private BooleanValidatorFactory() {
    // singleton
  }

  public static BooleanValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public BooleanValidator create(CellBuildUnitParam param) {

    Object additionalParam = param.getAdditionalParam();

    if (!(additionalParam instanceof BooleanParam)) {
      throw new IllegalArgumentException("the boolean validator additional param not satisfied, need [BooleanParam]");
    }

    return new BooleanValidator(((BooleanParam) additionalParam).getTrueStrings(), ((BooleanParam) additionalParam).getFalseStrings())
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));

  }

}
