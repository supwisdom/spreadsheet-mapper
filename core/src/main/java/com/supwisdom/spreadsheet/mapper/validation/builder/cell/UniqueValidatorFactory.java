package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.UniqueValidator;

/**
 * {@link UniqueValidator}工厂
 * Created by hanwen on 2017/1/22.
 */
public class UniqueValidatorFactory implements CellValidatorFactory<UniqueValidator> {

  private static final UniqueValidatorFactory INSTANCE = new UniqueValidatorFactory();

  private UniqueValidatorFactory() {
    // singleton
  }

  public static UniqueValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public UniqueValidator create(CellBuildUnitParam param) {
    return new UniqueValidator()
        .matchField(param.getMatchField())
        .errorMessage(param.getErrorMessage())
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]));
  }
}
