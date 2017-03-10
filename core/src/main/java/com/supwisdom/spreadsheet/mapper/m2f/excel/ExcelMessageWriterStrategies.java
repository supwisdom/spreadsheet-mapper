package com.supwisdom.spreadsheet.mapper.m2f.excel;

import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.shapes.Comment;
import com.supwisdom.spreadsheet.mapper.model.shapes.TextBox;

/**
 * {@link Message}写入时的策略常量
 * Created by hanwen on 2017/1/3.
 */
public class ExcelMessageWriterStrategies {

  private ExcelMessageWriterStrategies() {
    // singleton
  }

  /**
   * 注释
   *
   * @see Comment
   */
  public static final String COMMENT = "COMMENT";

  /**
   * 文本框
   *
   * @see TextBox
   */
  public static final String TEXT_BOX = "TEXT_BOX";
}
