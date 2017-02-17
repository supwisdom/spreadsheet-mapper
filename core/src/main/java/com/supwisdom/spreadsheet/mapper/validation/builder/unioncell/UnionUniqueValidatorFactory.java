package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionUniqueValidator;

/**
 * {@link UnionUniqueValidator}工厂
 * Created by hanwen on 2017/1/22.
 */
public class UnionUniqueValidatorFactory implements UnionCellValidatorFactory<UnionUniqueValidator> {

  private static final UnionUniqueValidatorFactory INSTANCE = new UnionUniqueValidatorFactory();

  private UnionUniqueValidatorFactory() {
    // singleton
  }

  public static UnionUniqueValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public UnionUniqueValidator create(UnionCellBuildUnitParam param) {
    return new UnionUniqueValidator()
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .errorMessage(param.getErrorMessage())
        .matchFields(param.getMatchFields().toArray(new String[0]));
  }

}
