package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;

/**
 * 单元格校验器
 * Created by hanwen on 2016/12/26.
 */
public interface CellValidator extends Dependant {

  /**
   * @return 匹配的field，对应{@link FieldMeta#getName()}
   */
  String getMatchField();

  /**
   * @return 错误消息
   */
  String getErrorMessage();

  /**
   * 验证单元格
   *
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   * @return true代表验证通过，false代表验证失败
   */
  boolean validate(Cell cell, FieldMeta fieldMeta);

}
