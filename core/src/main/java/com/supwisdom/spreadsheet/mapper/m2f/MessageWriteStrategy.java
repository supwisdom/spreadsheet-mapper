package com.supwisdom.spreadsheet.mapper.m2f;

import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Collection;

/**
 * {@link Message}写入策略
 * Created by hanwen on 2017/1/3.
 */
public interface MessageWriteStrategy {

  /**
   * @return {@link ExcelMessageWriteStrategies}
   */
  String getStrategy();

  /**
   * 写{@link Message}
   *
   * @param workbook {@link Workbook}
   * @param messages {@link Message}
   */
  void write(Workbook workbook, Collection<Message> messages);
}
