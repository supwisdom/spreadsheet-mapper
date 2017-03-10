package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.UniqueValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类似于{@link UniqueValidator}，只不过是同时判断多个单元格的值
 * Created by hanwen on 2016/12/1.
 */
public class UnionUniqueValidator extends UnionCellValidatorTemplate<UnionUniqueValidator> {

  private Set<List<String>> collectedValues = new HashSet<>();

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {

    List<String> valueList = new ArrayList<>();

    for (int i = 0; i < cells.size(); i++) {
      valueList.add(cells.get(i).getValue());
    }

    return collectedValues.add(valueList);
  }

}
