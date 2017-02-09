package com.supwisdom.spreadsheet.mapper.w2o.validation.rule;

import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.SingleCellValidator;

/**
 * factory to create single cell validator
 * <p>
 * Created by hanwen on 2017/1/22.
 */
public interface SingleCellValidatorFactory<V extends SingleCellValidator> extends DependencyValidatorFactory<V> {

  /**
   * create single cell validator
   *
   * @param param      {@link DependencyRuleParam}
   * @param matchField {@link SingleCellValidator#getMatchField()}
   * @return {@link SingleCellValidator}
   */
  V create(DependencyRuleParam param, String matchField);
}
