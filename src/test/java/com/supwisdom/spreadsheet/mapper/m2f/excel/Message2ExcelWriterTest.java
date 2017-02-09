package com.supwisdom.spreadsheet.mapper.m2f.excel;

import com.supwisdom.spreadsheet.mapper.m2f.MessageWriter;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hanwen on 2017/1/6.
 */
public class Message2ExcelWriterTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(Message2ExcelWriterTest.class);

  private File file;

  @BeforeClass
  public void before() throws IOException {
    LOGGER.debug("-------------------starting test message write helper-------------------");
    file = File.createTempFile("test", ".xlsx");
    LOGGER.debug(file.getAbsolutePath());
  }

  @Test
  public void testWrite() throws Exception {

    MessageWriter messageWriter = new Message2ExcelWriter();
    messageWriter.write(createErrorMessages(), new FileOutputStream(file));
  }

  private Collection<Message> createErrorMessages() {

    List<Message> messages = new ArrayList<>();

    messages.add(new MessageBean(MessageWriteStrategies.COMMENT, "test1", 1, 1, 1));
    messages.add(new MessageBean(MessageWriteStrategies.COMMENT, "test2", 1, 1, 1));
    messages.add(new MessageBean(MessageWriteStrategies.COMMENT, "test3", 1, 1, 1));
    messages.add(new MessageBean(MessageWriteStrategies.COMMENT, "test4", 1, 3, 3));

    messages.add(new MessageBean(MessageWriteStrategies.TEXT_BOX, "test5", 1));
    messages.add(new MessageBean(MessageWriteStrategies.TEXT_BOX, "test6", 1));
    messages.add(new MessageBean(MessageWriteStrategies.TEXT_BOX, "test7", 1));

    return messages;

  }

}
