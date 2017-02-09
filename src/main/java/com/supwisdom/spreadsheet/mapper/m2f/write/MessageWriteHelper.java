package com.supwisdom.spreadsheet.mapper.m2f.write;

import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.m2f.write.strategy.MessageWriteStrategy;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;

import java.io.OutputStream;
import java.util.Collection;

/**
 * message write helper
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public interface MessageWriteHelper {

  /**
   * {@link MessageWriteStrategy} unique with {@link MessageWriteStrategies} (one to one)
   *
   * @param messageWriteStrategy {@link MessageWriteStrategy}
   * @return {@link MessageWriteHelper}
   */
  MessageWriteHelper addMessageWriteStrategy(MessageWriteStrategy messageWriteStrategy);

  /**
   * write messages to supplied output stream
   *
   * @param messages     {@link Message}
   * @param outputStream notice close the stream
   */
  void write(Collection<Message> messages, OutputStream outputStream);
}
