package com.supwisdom.spreadsheet.mapper.w2f;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.io.OutputStream;

/**
 * 将{@link Workbook}写到{@link OutputStream}的工具
 * Created by hanwen on 2016/12/30.
 */
public interface WorkbookWriter {

  /**
   * 将 {@link Workbook} 写到@link OutputStream}
   *
   * @param workbook     {@link Workbook}
   * @param outputStream 输出流，注意调用本方法之后，需要client code自行关闭输出流
   */
  void write(Workbook workbook, OutputStream outputStream);
}
