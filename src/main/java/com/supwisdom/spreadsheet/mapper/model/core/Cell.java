package com.supwisdom.spreadsheet.mapper.model.core;

import java.io.Serializable;

/**
 * cell
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface Cell extends Serializable {

  /**
   * cell value
   *
   * @return value
   */
  String getValue();

  /**
   * cell column index
   *
   * @return 1-based
   */
  int getIndex();

  /**
   * @return the row of this
   */
  Row getRow();
}
