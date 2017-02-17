package com.supwisdom.spreadsheet.mapper.m2f;

import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import org.apache.poi.ss.usermodel.Workbook;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;

import java.util.Collection;

/**
 * message write strategy
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public interface MessageWriteStrategy {

  /**
   * @return {@link MessageWriteStrategies}
   */
  String getStrategy();

  /**
   * write messages
   *
   * @param workbook {@link Workbook}
   * @param messages {@link Message}
   */
  void write(Workbook workbook, Collection<Message> messages);
}
