package com.supwisdom.spreadsheet.mapper.model.msg;

import com.supwisdom.spreadsheet.mapper.model.shapes.Comment;
import com.supwisdom.spreadsheet.mapper.model.shapes.TextBox;

/**
 * {@link Message}写入时的策略常量
 * Created by hanwen on 2017/1/3.
 */
public class MessageWriteStrategies {

  private MessageWriteStrategies() {
    // default constructor
  }

  /**
   * 注释
   *
   * @see Comment
   */
  public static final String COMMENT = "comment";

  /**
   * 文本框
   *
   * @see TextBox
   */
  public static final String TEXT_BOX = "text_box";
}
