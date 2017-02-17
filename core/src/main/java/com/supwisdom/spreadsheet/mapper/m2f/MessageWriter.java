package com.supwisdom.spreadsheet.mapper.m2f;

import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;

import java.io.OutputStream;
import java.util.Collection;

/**
 * 将{@link Message}写到spreadsheet的工具
 * Created by hanwen on 2017/1/3.
 */
public interface MessageWriter {

  /**
   * {@link MessageWriteStrategy} unique with {@link MessageWriteStrategies} (one to one)
   *
   * @param messageWriteStrategy {@link MessageWriteStrategy}
   * @return {@link MessageWriter}
   */
  MessageWriter addMessageWriteStrategy(MessageWriteStrategy messageWriteStrategy);

  /**
   * write messages to supplied output stream
   *
   * @param messages     {@link Message}
   * @param outputStream notice close the stream
   */
  void write(Collection<Message> messages, OutputStream outputStream);
}
