package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;

/**
 * factory to create start cell validator
 * Created by hanwen on 2017/1/22.
 */
public interface CellValidatorFactory<V extends CellValidator> {

  /**
   * create start cell validator
   *
   * @param param {@link CellBuildUnitParam}
   * @return {@link CellValidator}
   */
  V create(CellBuildUnitParam param);
}
