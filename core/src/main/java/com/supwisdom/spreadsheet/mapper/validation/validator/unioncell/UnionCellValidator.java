package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * 联合单元格校验器
 * Created by hanwen on 2017/1/20.
 */
public interface UnionCellValidator extends Dependant {

  /**
   * @return 匹配的field（复数），对应{@link FieldMeta#getName()}
   */
  LinkedHashSet<String> getMatchFields();

  /**
   * @return 错误消息
   */
  String getErrorMessage();

  /**
   * 验证多个单元格
   *
   * @param cells      {@link Cell}
   * @param fieldMetas {@link FieldMeta}
   * @return true代表验证通过，false代表验证失败
   */
  boolean validate(List<Cell> cells, List<FieldMeta> fieldMetas);

}
