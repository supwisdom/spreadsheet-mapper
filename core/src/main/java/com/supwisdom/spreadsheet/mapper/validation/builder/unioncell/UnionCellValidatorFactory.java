package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

/**
 * factory to create multi cell validator
 * Created by hanwen on 2017/1/22.
 */
public interface UnionCellValidatorFactory<V extends UnionCellValidator> {

  /**
   * create a multi cell validator
   *
   * @param unionCellBuildUnitParam
   * @return {@link UnionCellValidator}
   */
  V create(UnionCellBuildUnitParam unionCellBuildUnitParam);

}
