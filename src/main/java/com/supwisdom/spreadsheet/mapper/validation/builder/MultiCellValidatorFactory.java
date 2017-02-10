package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.MultiCellValidator;

import java.util.List;

/**
 * factory to create multi cell validator
 * <p>
 * Created by hanwen on 2017/1/22.
 */
public interface MultiCellValidatorFactory<V extends MultiCellValidator> extends ValidatorFactory<V> {

  /**
   * create a multi cell validator
   *
   * @param param       {@link ValidatorFactoryParam}
   * @param matchFields {@link MultiCellValidator#getMatchFields()}
   * @return {@link MultiCellValidator}
   */
  V create(ValidatorFactoryParam param, List<String> matchFields);
}
