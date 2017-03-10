package com.supwisdom.spreadsheet.mapper.m2f.excel;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReadException;
import com.supwisdom.spreadsheet.mapper.m2f.MessageWriteStrategy;
import com.supwisdom.spreadsheet.mapper.m2f.MessageWriter;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.w2f.WorkbookWriteException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 将{@link Message}写到excel文件的工具
 * Created by hanwen on 2017/1/3.
 */
public class ExcelMessageWriter implements MessageWriter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExcelMessageWriter.class);

  private Map<String, MessageWriteStrategy> strategy2writeStrategy = new HashMap<>();

  private Workbook workbook;

  {
    addMessageWriteStrategy(new SingleCommentInCellStrategy());
    addMessageWriteStrategy(new SingleTextBoxInSheetStrategy());
  }

  /**
   * 创建xlsx的{@link ExcelMessageWriter}
   *
   * @see #ExcelMessageWriter(boolean)
   */
  public ExcelMessageWriter() {
    this(true);
  }

  /**
   * 构造函数
   *
   * @param xlsx true 使用 {@link XSSFWorkbook}，false 使用 {@link HSSFWorkbook}
   */
  public ExcelMessageWriter(boolean xlsx) {
    workbook = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();
  }

  /**
   * 构造函数，区别在于预先读入Excel文件，然后{@link Message}都写到这个Excel文件中。
   *
   * @param inputStream auto close
   */
  public ExcelMessageWriter(InputStream inputStream) {
    try {
      workbook = WorkbookFactory.create(inputStream);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookReadException(e);
    }
  }

  @Override
  public MessageWriter addMessageWriteStrategy(MessageWriteStrategy messageWriteStrategy) {
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
        throw new WorkbookWriteException("No message write strategy found for [" + writeStrategy + "]");
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
