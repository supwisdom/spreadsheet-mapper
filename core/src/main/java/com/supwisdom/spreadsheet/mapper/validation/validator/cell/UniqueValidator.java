package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 有状态的Validator，在多次执行校验的过程中，判断{@link Cell#getValue()}是否重复出现了
 * Created by hanwen on 2017/1/11.
 */
public class UniqueValidator extends CellValidatorTemplate<UniqueValidator> {

  private Set<String> cellValueHolder = new HashSet<>();

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {

    String cellValue = cell.getValue();
    if (StringUtils.isBlank(cellValue)) {
      return true;
    }

    return cellValueHolder.add(cellValue);
  }

}
