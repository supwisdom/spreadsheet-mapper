package com.supwisdom.spreadsheet.mapper.validation.validator.row;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

import java.util.Set;

/**
 * 行校验器
 * Created by hanwen on 4/26/16.
 */
public interface RowValidator {

  /**
   * @return 错误消息
   */
  String getErrorMessage();

  /**
   * 验证行
   *
   * @param row       {@link Row}
   * @param sheetMeta {@link SheetMeta}
   * @return true代表验证通过，false代表验证失败
   */
  boolean validate(Row row, SheetMeta sheetMeta);

  /**
   * @return 验证失败相关的field
   */
  Set<String> getErrorFields();
}
