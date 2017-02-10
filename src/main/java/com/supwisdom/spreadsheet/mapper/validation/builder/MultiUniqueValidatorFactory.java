package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.MultiUniqueValidator;

import java.util.List;

/**
 * Created by hanwen on 2017/1/22.
 */
public class MultiUniqueValidatorFactory implements MultiCellValidatorFactory<MultiUniqueValidator> {

  private static final MultiUniqueValidatorFactory INSTANCE = new MultiUniqueValidatorFactory();

  private MultiUniqueValidatorFactory() {
    // singleton
  }

  public static MultiUniqueValidatorFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public MultiUniqueValidator create(ValidatorFactoryParam param, List<String> matchFields) {
    return new MultiUniqueValidator()
        .group(param.getGroup())
        .dependsOn(param.getDependsOn().toArray(new String[0]))
        .errorMessage(param.getErrorMessage())
        .matchFields(matchFields.toArray(new String[0]));
  }
}
