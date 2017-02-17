package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

/**
 * 必填校验器
 * Created by hanwen on 2017/1/11.
 */
public class RequireValidator extends CellValidatorTemplate<RequireValidator> {

  /**
   * 如果{@link Cell#getValue()}是{@link StringUtils#isBlank(CharSequence)}则返回false
   *
   * @param cell      {@link Cell}
   * @param fieldMeta 和cell对应的{@link FieldMeta}
   * @return true代表Cell.value不是blank的，false反之
   */
  @Override
  final public boolean validate(Cell cell, FieldMeta fieldMeta) {
    return doValidate(cell, fieldMeta);
  }

  @Override
  final protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return StringUtils.isNotBlank(cell.getValue());
  }

}
