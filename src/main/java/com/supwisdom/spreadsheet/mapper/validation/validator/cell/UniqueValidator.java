package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
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
public class UniqueValidator extends CustomSingleCellValidator<UniqueValidator> {

  private Set<String> cellValueHolder = new HashSet<>();

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {

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
