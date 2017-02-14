package com.supwisdom.spreadsheet.mapper.model.shapes;

import java.io.Serializable;

/**
 * excel cell comment, this is a warp of poi cell comment and simplify api, because this comment just propose to show messages on excel.
 * <p>
 * Created by hanwen on 15-12-17.
 */
public interface Comment extends Serializable {

  /**
   * @return message on comment
   */
  String getMessage();

  /**
   * @return comment's rectangle length
   */
  int getLength();

  /**
   * @return comment's rectangle height
   */
  int getHeight();

  /**
   * sheet index of comment location
   *
   * @return 1-based
   */
  int getSheetIndex();

  /**
   * row num of comment location
   *
   * @return 1-based
   */
  int getRowIndex();

  /**
   * column num of comment location
   *
   * @return 1-based
   */
  int getColumnIndex();
}
