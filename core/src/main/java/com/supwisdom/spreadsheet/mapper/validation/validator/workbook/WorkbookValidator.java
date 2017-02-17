package com.supwisdom.spreadsheet.mapper.validation.validator.workbook;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.Set;

/**
 * 工作簿校验器
 * Created by hanwen on 4/26/16.
 */
public interface WorkbookValidator {

  /**
   * @return 错误消息
   */
  String getErrorMessage();

  /**
   * 验证工作簿
   *
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return true代表验证通过，false代表验证失败
   */
  boolean validate(Workbook workbook, WorkbookMeta workbookMeta);

  /**
   * @return 校验失败的sheet的index
   */
  Set<Integer> getErrorSheetIndices();
}
