package com.supwisdom.spreadsheet.mapper.m2f.write;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.f2w.read.WorkbookReadException;
import com.supwisdom.spreadsheet.mapper.m2f.write.strategy.MessageWriteStrategy;
import com.supwisdom.spreadsheet.mapper.m2f.write.strategy.SingleCommentInCellStrategy;
import com.supwisdom.spreadsheet.mapper.m2f.write.strategy.SingleTextBoxInSheetStrategy;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.w2f.write.WorkbookWriteException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * message to excel writer decorator
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public class Message2ExcelWriteHelper implements MessageWriteHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(Message2ExcelWriteHelper.class);

  private Map<String, MessageWriteStrategy> strategy2writeStrategy = new HashMap<>();

  private Workbook workbook;

  {
    addMessageWriteStrategy(new SingleCommentInCellStrategy());
    addMessageWriteStrategy(new SingleTextBoxInSheetStrategy());
  }

  /**
   * default new excel with xlsx
   *
   * @see #Message2ExcelWriteHelper(boolean)
   */
  public Message2ExcelWriteHelper() {
    this(true);
  }

  /**
   * this will create a new excel workbook to write messages
   *
   * @param xlsx true use {@link XSSFWorkbook} else use {@link HSSFWorkbook}
   */
  public Message2ExcelWriteHelper(boolean xlsx) {
    workbook = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();
  }

  /**
   * this will copy a excel workbook from supplied input stream to write messages
   *
   * @param inputStream auto close
   */
  public Message2ExcelWriteHelper(InputStream inputStream) {
    try {
      workbook = WorkbookFactory.create(inputStream);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookReadException(e);
    }
  }

  @Override
  public MessageWriteHelper addMessageWriteStrategy(MessageWriteStrategy messageWriteStrategy) {
    if (messageWriteStrategy == null) {
      throw new IllegalArgumentException("message write strategy can not be null");
    }

    String strategy = messageWriteStrategy.getStrategy();
    if (strategy2writeStrategy.containsKey(strategy)) {
      throw new IllegalArgumentException("message writer contains multi write strategy[" + strategy + "]");
    }

    strategy2writeStrategy.put(strategy, messageWriteStrategy);
    return this;
  }

  @Override
  public void write(Collection<Message> messages, OutputStream outputStream) {
    Map<String, Collection<Message>> messageWriteStrategyMap = buildMessageWriteStrategyMap(messages);
    for (String writeStrategy : messageWriteStrategyMap.keySet()) {
      MessageWriteStrategy messageWriteStrategy = strategy2writeStrategy.get(writeStrategy);
      if (messageWriteStrategy == null) {
        throw new WorkbookWriteException("no message write strategy of [" + writeStrategy + "]");
      }

      messageWriteStrategy.write(workbook, messageWriteStrategyMap.get(writeStrategy));
    }

    try {
      workbook.write(outputStream);
    } catch (IOException e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookWriteException(e);
    } finally {

      try {
        workbook.close();
      } catch (IOException e) {
        LOGGER.error(ExceptionUtils.getStackTrace(e));
      }
    }
  }

  private Map<String, Collection<Message>> buildMessageWriteStrategyMap(Collection<Message> messages) {
    Map<String, Collection<Message>> messageWriteStrategyMap = new HashMap<>();

    for (Message message : messages) {
      String messageWriteStrategy = message.getMessageWriteStrategy();

      if (!messageWriteStrategyMap.containsKey(messageWriteStrategy)) {
        messageWriteStrategyMap.put(messageWriteStrategy, new ArrayList<Message>());
      }
      messageWriteStrategyMap.get(messageWriteStrategy).add(message);
    }

    return messageWriteStrategyMap;
  }
}
