package com.supwisdom.spreadsheet.mapper.model.msg;

import java.io.Serializable;

/**
 * spreadsheet message
 * <p>
 * Created by hanwen on 2016/12/27.
 */
public interface Message extends Serializable {

  /**
   * if use {@link MessageWriteStrategies#TEXT_BOX}, {@link #getRowIndex()} &amp; {@link #getColumnIndex()} will ignore
   *
   * @return use which message write strategy to write message
   * @see MessageWriteStrategies
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
