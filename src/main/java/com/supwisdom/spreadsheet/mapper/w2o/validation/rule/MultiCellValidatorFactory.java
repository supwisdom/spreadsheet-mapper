package com.supwisdom.spreadsheet.mapper.w2o.validation.rule;

import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.MultiCellValidator;

import java.util.List;

/**
 * factory to create multi cell validator
 * <p>
 * Created by hanwen on 2017/1/22.
 */
public interface MultiCellValidatorFactory<V extends MultiCellValidator> extends DependencyValidatorFactory<V> {

  /**
   * create a multi cell validator
   *
   * @param param       {@link DependencyRuleParam}
   * @param matchFields {@link MultiCellValidator#getMatchFields()}
   * @return {@link MultiCellValidator}
   */
  V create(DependencyRuleParam param, List<String> matchFields);
}
