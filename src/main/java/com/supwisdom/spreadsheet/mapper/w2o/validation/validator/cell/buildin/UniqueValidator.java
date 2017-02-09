package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.buildin;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.CustomSingleCellValidatorAdapter;
import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * value unique in template validator
 *
 * like {@link MultiUniqueValidator},
 * this validator only check one cell value if unique.
 * </pre>
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class UniqueValidator extends CustomSingleCellValidatorAdapter<UniqueValidator> {

  private Set<String> cellValueHolder = new HashSet<>();

  @Override
  protected UniqueValidator getThis() {
    return this;
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {

    String cellValue = cell.getValue();
    if (StringUtils.isBlank(cellValue)) {
      return true;
    }

    if (cellValueHolder.contains(cellValue)) {
      return false;
    }

    cellValueHolder.add(cellValue);
    return true;
  }
}
