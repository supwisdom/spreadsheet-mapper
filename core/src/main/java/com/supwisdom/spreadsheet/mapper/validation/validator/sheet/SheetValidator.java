package com.supwisdom.spreadsheet.mapper.validation.validator.sheet;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

/**
 * 工作表校验器
 * Created by hanwen on 2016/12/23.
 */
public interface SheetValidator {

  /**
   * @return 错误消息
   */
  String getErrorMessage();

  /**
   * 验证工作表
   *
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   * @return true代表验证通过，false代表验证失败
   */
  boolean validate(Sheet sheet, SheetMeta sheetMeta);

}
