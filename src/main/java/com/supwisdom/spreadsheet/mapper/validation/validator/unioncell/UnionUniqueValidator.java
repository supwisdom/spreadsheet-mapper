package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * value union unique validator, it useful when you want validate some cells value union unique.
 * eg:
 * if you excel files has person.idCardNumber and person.idCardType, you will want check if person's identify unique,
 * when the excel files has duplicate person identify this validator will get false.
 * </pre>
 * Created by hanwen on 2016/12/1.
 */
public class UnionUniqueValidator extends CustomUnionCellValidator<UnionUniqueValidator> {

  private Set<List<String>> collectedValues = new HashSet<>();

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {

    List<String> valueList = new ArrayList<>();

    for (int i = 0; i < cells.size(); i++) {
      valueList.add(cells.get(i).getValue());
    }

    if (collectedValues.contains(valueList)) {
      return false;
    }

    collectedValues.add(valueList);
    return true;
  }

}
