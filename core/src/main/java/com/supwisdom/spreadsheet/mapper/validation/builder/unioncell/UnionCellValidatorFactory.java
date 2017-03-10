package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

/**
 * {@link UnionCellValidator}工厂
 * Created by hanwen on 2017/1/22.
 */
public interface UnionCellValidatorFactory<V extends UnionCellValidator> {

  /**
   * @param unionCellBuildUnitParam {@link UnionCellBuildUnitParam}
   * @return {@link UnionCellValidator}
   */
  V create(UnionCellBuildUnitParam unionCellBuildUnitParam);

}
