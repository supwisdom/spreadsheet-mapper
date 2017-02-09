package com.supwisdom.spreadsheet.mapper.w2f;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.io.OutputStream;

/**
 * workbook write helper
 * <p>
 * Created by hanwen on 2016/12/30.
 */
public interface WorkbookWriteHelper {

  /**
   * write {@link Workbook} to supplied output stream
   *
   * @param workbook     {@link Workbook}
   * @param outputStream notice close the stream
   */
  void write(Workbook workbook, OutputStream outputStream);
}
