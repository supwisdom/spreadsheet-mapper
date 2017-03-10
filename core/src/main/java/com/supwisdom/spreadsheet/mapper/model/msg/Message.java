package com.supwisdom.spreadsheet.mapper.model.msg;

import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriterStrategies;

import java.io.Serializable;

/**
 * spreadsheet message
 * <p>
 * Created by hanwen on 2016/12/27.
 */
public interface Message extends Serializable {

  /**
   * if use {@link ExcelMessageWriterStrategies#TEXT_BOX}, {@link #getRowIndex()} &amp; {@link #getColumnIndex()} will ignore
   *
   * @return use which message write strategy to write message
   * @see ExcelMessageWriterStrategies
   */
  String getMessageWriteStrategy();

  /**
   * @return message show on which sheet
   */
  int getSheetIndex();

  /**
   * @return message on which row
   */
  Integer getRowIndex();

  /**
   * @return message on which column
   */
  Integer getColumnIndex();

  /**
   * @return message
   */
  String getMessage();
}
