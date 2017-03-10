package com.supwisdom.spreadsheet.mapper.m2f.excel;

import com.supwisdom.spreadsheet.mapper.m2f.MessageWriter;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hanwen on 2017/1/6.
 */
public class ExcelMessageWriterTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExcelMessageWriterTest.class);

  @DataProvider
  public Object[][] messages() {

    List<Message> messages = new ArrayList<>();

    messages.add(new MessageBean(ExcelMessageWriterStrategies.COMMENT, "test1", 1, 1, 1));
    messages.add(new MessageBean(ExcelMessageWriterStrategies.COMMENT, "test2", 1, 1, 2));
    messages.add(new MessageBean(ExcelMessageWriterStrategies.COMMENT, "test3", 1, 1, 3));
    messages.add(new MessageBean(ExcelMessageWriterStrategies.COMMENT, "test4", 1, 1, 4));

    messages.add(new MessageBean(ExcelMessageWriterStrategies.TEXT_BOX, "test5", 1));
    messages.add(new MessageBean(ExcelMessageWriterStrategies.TEXT_BOX, "test6", 1));
    messages.add(new MessageBean(ExcelMessageWriterStrategies.TEXT_BOX, "test7", 1));

    return new Object[][] {
        new Object[] { messages }
    };

  }

  @Test(dataProvider = "messages")
  public void testWriteEmptyXls(Collection<Message> messages) throws Exception {

    File file = File.createTempFile("test", ".xls");
    MessageWriter messageWriter = new ExcelMessageWriter(false);
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      messageWriter.write(messages, outputStream);
    }

    LOGGER.info("testWriteEmptyXls: " + file.getAbsolutePath());
  }

  @Test(dataProvider = "messages")
  public void testWriteEmptyXlsx(Collection<Message> messages) throws Exception {

    File file = File.createTempFile("test", ".xlsx");
    MessageWriter messageWriter = new ExcelMessageWriter(true);
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      messageWriter.write(messages, outputStream);
    }
    LOGGER.info("testWriteEmptyXlsx: " + file.getAbsolutePath());

  }

  @Test(dataProvider = "messages")
  public void testWriteNonEmptyXls(Collection<Message> messages) throws Exception {

    File file = File.createTempFile("test", ".xls");
    try (InputStream stream = getClass().getResourceAsStream("test.xls")) {
      MessageWriter messageWriter = new ExcelMessageWriter(stream);
      try (FileOutputStream outputStream = new FileOutputStream(file)) {
        messageWriter.write(messages, outputStream);
      }
    }
    LOGGER.info("testWriteNonEmptyXls: " + file.getAbsolutePath());
  }

  @Test(dataProvider = "messages")
  public void testWriteNonEmptyXlsx(Collection<Message> messages) throws Exception {

    File file = File.createTempFile("test", ".xlsx");
    try (InputStream stream = getClass().getResourceAsStream("test.xlsx")) {
      MessageWriter messageWriter = new ExcelMessageWriter(stream);
      try (FileOutputStream outputStream = new FileOutputStream(file)) {
        messageWriter.write(messages, outputStream);
      }
    }
    LOGGER.info("testWriteNonEmptyXlsx: " + file.getAbsolutePath());
  }

  @Test(dataProvider = "messages")
  public void testWriteNonEmptyXls2(Collection<Message> messages) throws Exception {

    File file = File.createTempFile("test", ".xls");
    try (InputStream stream = getClass().getResourceAsStream("test-with-comments-textboxes.xls")) {
      MessageWriter messageWriter = new ExcelMessageWriter(stream);
      try (FileOutputStream outputStream = new FileOutputStream(file)) {
        messageWriter.write(messages, outputStream);
      }
    }
    LOGGER.info("testWriteNonEmptyXls2: " + file.getAbsolutePath());
  }

  @Test(dataProvider = "messages")
  public void testWriteNonEmptyXlsx2(Collection<Message> messages) throws Exception {

    File file = File.createTempFile("test", ".xlsx");
    try (InputStream stream = getClass().getResourceAsStream("test-with-comments-textboxes.xlsx")) {
      MessageWriter messageWriter = new ExcelMessageWriter(stream);
      try (FileOutputStream outputStream = new FileOutputStream(file)) {
        messageWriter.write(messages, outputStream);
      }
    }
    LOGGER.info("testWriteNonEmptyXlsx2: " + file.getAbsolutePath());
  }

}
