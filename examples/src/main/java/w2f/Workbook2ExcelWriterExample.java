package w2f;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.w2f.WorkbookWriter;
import com.supwisdom.spreadsheet.mapper.w2f.excel.Workbook2ExcelWriter;
import o2w.Object2WorkbookExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 将Workbook写到excel文件的例子
 * Created by qianjia on 2017/3/14.
 */
public class Workbook2ExcelWriterExample {

  private static final Logger LOGGER = LoggerFactory.getLogger(Workbook2ExcelWriterExample.class);

  public static void main(String[] args) throws IOException {

    Object2WorkbookExample object2WorkbookExample = new Object2WorkbookExample();
    Workbook workbook = object2WorkbookExample.convertToWorkbook();
    WorkbookWriter workbookWriter = new Workbook2ExcelWriter();

    File tempFile = File.createTempFile("object-2-workbook-result", ".xlsx");

    try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
      workbookWriter.write(workbook, outputStream);
    }

    LOGGER.info("Write file at {}", tempFile.getAbsolutePath());

  }

}
