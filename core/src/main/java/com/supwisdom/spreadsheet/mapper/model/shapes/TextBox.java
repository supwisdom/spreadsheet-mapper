package com.supwisdom.spreadsheet.mapper.model.shapes;

import java.io.Serializable;

/**
 * excel text box, this is a warp of poi text box and simplify api, because this text box just propose to show messages on excel.
 * <p>
 * Created by hanwen on 2016/12/27.
 */
public interface TextBox extends Serializable {

  /**
   * @return message on text box
   */
  String getMessage();

  /**
   * @return style
   * @see TextBoxStyle
   */
  TextBoxStyle getStyle();

  /**
   * @return sheet index of text box at
   */
  int getSheetIndex();
}
