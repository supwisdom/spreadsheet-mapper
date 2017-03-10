package m2f;

import com.supwisdom.spreadsheet.mapper.m2f.MessageWriter;
import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriter;
import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Message写到Excel的例子
 * Created by qianjia on 2017/3/9.
 */
public class Message2ExcelWriterExample {

  public static void main(String[] args) throws IOException {

    List<Message> messages = new ArrayList<>();
    messages.add(new MessageBean(ExcelMessageWriteStrategies.COMMENT, "COMMENT123", 1, 1, 1));
    messages.add(new MessageBean(ExcelMessageWriteStrategies.TEXT_BOX, "TEXT_BOX", 1, 1, 2));

    Message2ExcelWriterExample example = new Message2ExcelWriterExample();
    example.writeToXls(messages);
    example.writeToXlsx(messages);
  }

  public void writeToXls(List<Message> messages) throws IOException {

    MessageWriter messageWriter = null;
    try (InputStream resourceAsStream = Message2ExcelWriterExample.class.getResourceAsStream("test.xls")) {
      // 读取excel内容
      messageWriter = new ExcelMessageWriter(resourceAsStream);
    }

    File tempFile = File.createTempFile("test", ".xls");
    messageWriter.write(messages, new FileOutputStream(tempFile));

    System.out.println(tempFile.getAbsolutePath());

  }

  public void writeToXlsx(List<Message> messages) throws IOException {

    MessageWriter messageWriter = null;
    try (InputStream resourceAsStream = Message2ExcelWriterExample.class.getResourceAsStream("test.xlsx")) {
      // 读取excel内容
      messageWriter = new ExcelMessageWriter(resourceAsStream);
    }

    File tempFile = File.createTempFile("test", ".xlsx");
    try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
      messageWriter.write(messages, outputStream);
    }

    System.out.println(tempFile.getAbsolutePath());

  }
}
