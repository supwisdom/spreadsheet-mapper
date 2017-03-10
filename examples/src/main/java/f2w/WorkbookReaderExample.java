package f2w;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReader;
import com.supwisdom.spreadsheet.mapper.f2w.excel.Excel2WorkbookReader;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取工作表的例子
 * Created by qianjia on 2017/3/9.
 */
public class WorkbookReaderExample {

  public static void main(String[] args) {

    WorkbookReaderExample example = new WorkbookReaderExample();
    example.readXls();
    example.readXlsx();
  }

  public void readXls() {

    System.out.println("--------Start Read Xls--------");
    WorkbookReader workbookReader = new Excel2WorkbookReader();
    try (InputStream inputStream = WorkbookReaderExample.class.getResourceAsStream("test.xls")) {
      Workbook workbook = workbookReader.read(inputStream);
      print(workbook);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("--------End Read Xls--------");

  }

  public void readXlsx() {

    System.out.println("--------Start Read Xlsx--------");
    WorkbookReader workbookReader = new Excel2WorkbookReader();
    Workbook workbook = workbookReader.read(WorkbookReaderExample.class.getResourceAsStream("test.xlsx"));
    print(workbook);
    System.out.println("--------End Read Xlsx--------");

  }

  public void print(Workbook workbook) {

    for (Sheet sheet : workbook.getSheets()) {

      System.out.println("--------Start Sheet--------");
      System.out.println(sheet);
      for (Row row : sheet.getRows()) {
        System.out.println("--------Start Row--------");
        System.out.println(row);
        System.out.println("--------Start Cell--------");
        for (Cell cell : row.getCells()) {
          System.out.println(cell.toString());
        }
        System.out.println("--------End Cell--------");
        System.out.println("--------End Row--------");
      }
      System.out.println("--------End Sheet--------");
    }

  }
}
