package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.validation.validator.workbook.WorkbookValidator;

import java.util.List;

/**
 * 工作簿校验工作
 * Created by hanwen on 2017/1/4.
 */
public interface WorkbookValidationJob<T extends WorkbookValidationJob<T>> {

  /**
   * 添加{@link WorkbookValidator}
   *
   * @param workbookValidator {@link WorkbookValidator}
   * @return 自己
   */
  T addValidator(WorkbookValidator workbookValidator);

  /**
   * 添加{@link SheetValidationJob}。
   * 如果Workbook有多个Sheet，添加的顺序应该和Workbook中的Sheet的顺序对应。
   *
   * @param sheetValidationJob {@link SheetValidationJob}
   * @return 自己
   */
  T addSheetValidationJob(SheetValidationJob sheetValidationJob);

  /**
   * 先执行{@link WorkbookValidator}，然后执行{@link SheetValidationJob#validate(Sheet, SheetMeta)}
   *
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return true代表校验通过，false代表失败
   */
  boolean validate(Workbook workbook, WorkbookMeta workbookMeta);

  /**
   * @return 失败消息
   */
  List<Message> getErrorMessages();
}
