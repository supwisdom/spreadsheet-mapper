package com.supwisdom.spreadsheet.mapper.f2w;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.io.InputStream;

/**
 * 读取{@link InputStream}并转换成{@link Workbook}的工具
 * Created by hanwen on 2016/12/30.
 */
public interface WorkbookReader {

  /**
   * 读取{@link InputStream}并转换成{@link Workbook}
   *
   * @param inputStream 本方法在读取完毕后会自行关闭输入流
   * @return {@link Workbook}
   */
  Workbook read(InputStream inputStream);
}
