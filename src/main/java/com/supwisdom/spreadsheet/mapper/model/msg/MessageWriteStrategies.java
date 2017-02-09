package com.supwisdom.spreadsheet.mapper.model.msg;

import com.supwisdom.spreadsheet.mapper.model.shapes.Comment;
import com.supwisdom.spreadsheet.mapper.model.shapes.TextBox;

/**
 * message writer strategies
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public class MessageWriteStrategies {

  private MessageWriteStrategies() {
    // default constructor
  }

  /**
   * use comment
   *
   * @see Comment
   */
  public static final String COMMENT = "comment";

  /**
   * use text box
   *
   * @see TextBox
   */
  public static final String TEXT_BOX = "text_box";
}
