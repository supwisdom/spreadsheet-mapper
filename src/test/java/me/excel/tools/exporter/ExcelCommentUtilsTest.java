package me.excel.tools.exporter;

import me.excel.tools.model.excel.*;
import org.apache.poi.util.TempFile;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 15-12-21.
 */
public class ExcelCommentUtilsTest {
  
  @Test
  public void testWriteToFile() throws Exception {
    ExcelWorkbook excelWorkbook = new ExcelWorkbookBean();

    ExcelSheet excelSheet = new ExcelSheetBean();
    excelWorkbook.addSheet(excelSheet);

    ExcelRowBean excelRow1 = new ExcelRowBean(1);
    excelSheet.addRow(excelRow1);
    ExcelCellBean commentCell = new ExcelCellBean(1, 1, "student.code", "111111");

    excelRow1.addCell(commentCell);
    excelRow1.addCell(new ExcelCellBean(1, 2, "student.name", "std1"));
    excelRow1.addCell(new ExcelCellBean(1, 3, "student.age", "18"));
    excelRow1.addCell(new ExcelCellBean(1, 4, "student.enrollDate", "2015-09-01"));

    ExcelRowBean excelRow2 = new ExcelRowBean(2);
    excelRow2.addCell(new ExcelCellBean(1, 1, "student.code", "2222"));
    excelRow2.addCell(new ExcelCellBean(1, 2, "student.name", "std2"));
    excelRow2.addCell(new ExcelCellBean(1, 3, "student.age", "18"));
    excelRow2.addCell(new ExcelCellBean(1, 4, "student.enrollDate", "2015-09-01"));
    excelSheet.addRow(excelRow2);

    UserFileExporter fileExporter = new ExcelFileExporter(excelWorkbook);

    File file = TempFile.createTempFile("test", ".xlsx");

    fileExporter.export(new FileOutputStream(file));


    ExcelCellCommentBean excelCellComment = new ExcelCellCommentBean();
    excelCellComment.addComment("student\'s code");
    commentCell.setComment(excelCellComment);

    List<ExcelCellComment> commentList = new ArrayList<>();
    commentList.add(commentCell.getComment());
    ExcelCommentUtils.writeToFile(file, commentList);
  }
}