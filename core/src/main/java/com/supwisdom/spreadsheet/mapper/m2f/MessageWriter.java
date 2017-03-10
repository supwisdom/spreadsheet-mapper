package com.supwisdom.spreadsheet.mapper.m2f;

import com.supwisdom.spreadsheet.mapper.model.msg.Message;

import java.io.OutputStream;
import java.util.Collection;

/**
 * 将{@link Message}写到spreadsheet的工具
 * Created by hanwen on 2017/1/3.
 */
public interface MessageWriter {

  /**
   * 添加 {@link MessageWriteStrategy}
   *
   * @param messageWriteStrategy {@link MessageWriteStrategy}
   * @return {@link MessageWriter}
   */
  MessageWriter addMessageWriteStrategy(MessageWriteStrategy messageWriteStrategy);

  /**
   * 写 {@link Message} 到输出流
   *
   * @param messages     {@link Message}
   * @param outputStream 输出流，注意这里不会关闭此流
   */
  void write(Collection<Message> messages, OutputStream outputStream);
}
