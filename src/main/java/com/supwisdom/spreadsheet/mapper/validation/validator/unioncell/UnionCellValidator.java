package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * multi cells validator, after row validators.
 * Created by hanwen on 2017/1/20.
 */
public interface UnionCellValidator extends Dependant {

  /**
   * 获得匹配的field组
   *
   * @return
   */
  LinkedHashSet<String> getMatchFields();

  /**
   * the error message will be collected when validator failure if error message is not blank
   *
   * @return validate error message
   */
  String getErrorMessage();

  /**
   * validate supplied cells, the sequence of cells and field metas is
   *
   * @param cells      {@link Cell}
   * @param fieldMetas {@link FieldMeta}
   * @return true if pass
   */
  boolean validate(List<Cell> cells, List<FieldMeta> fieldMetas);

}
