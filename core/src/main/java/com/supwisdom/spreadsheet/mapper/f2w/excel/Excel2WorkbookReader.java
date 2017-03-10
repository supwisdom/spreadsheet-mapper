package com.supwisdom.spreadsheet.mapper.f2w.excel;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReadException;
import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReader;
import com.supwisdom.spreadsheet.mapper.model.core.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取Excel文件的工具，支持.xls、.xlsx格式。单元格的里的所有内容都会被转换成String。<br>
 * 支持以下单元格格式：
 * <ol>
 *   <li>常规-数字。转换结果和单元格内容一致。</li>
 *   <li>常规-文本。转换结果和单元格内容一致。</li>
 *   <li>常规-布尔。转换为true 或者 false。</li>
 *   <li>数值。转换结果和单元格内容一致。</li>
 *   <li>货币。转换结果为数字，没有货币符号</li>
 *   <li>会计专用。转换结果为数字，没有货币符号</li>
 *   <li>日期。转换成EEE MMM dd HH:mm:ss zzz yyyy的字符串</li>
 *   <li>时间。和日期一样，不过要注意年月日是1899-12-31</li>
 *   <li>百分比。转换结果为数字，没有百分号。</li>
 *   <li>分数。转换结果为数字，没有分数。</li>
 *   <li>科学计数。转换结果为数字，没有科学记数法。</li>
 *   <li>文本。转换结果和单元格内容一致。</li>
 *   <li>特殊。转换结果为数字。</li>
 *   <li>自定义。如果是日期，转换结果同日期。</li>
 *   <li>公式。不支持</li>
 * </ol>
 * Created by hanwen on 2017/1/3.
 */
public class Excel2WorkbookReader implements WorkbookReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(Excel2WorkbookReader.class);

  private org.apache.poi.ss.usermodel.Workbook workbook;

  public Workbook read(InputStream inputStream) {

    Workbook excelWorkbook = new WorkbookBean();
    try {

      if (inputStream.available() == 0) {
        return excelWorkbook;
      }

      workbook = WorkbookFactory.create(inputStream);

      int sheetCount = workbook.getNumberOfSheets();

      for (int i = 0; i < sheetCount; i++) {

        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(i);

        if (sheet == null) {
          continue;
        }

        Sheet excelSheet = createSheet(sheet);
        excelWorkbook.addSheet(excelSheet);

        int maxColNum = getMaxColNum(sheet);
        for (int j = 0; j <= sheet.getLastRowNum(); j++) {

          org.apache.poi.ss.usermodel.Row row = sheet.getRow(j);
          Row excelRow = createRow();
          excelSheet.addRow(excelRow);

          for (int k = 0; k < maxColNum; k++) {

            org.apache.poi.ss.usermodel.Cell cell = row.getCell(k);
            excelRow.addCell(createCell(cell));
          }
        }
      }

      return excelWorkbook;
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookReadException(e);
    } finally {

      try {
        workbook.close();
      } catch (IOException e) {
        LOGGER.error(ExceptionUtils.getStackTrace(e));
      }
    }
  }

  private Sheet createSheet(org.apache.poi.ss.usermodel.Sheet sheet) {
    String sheetName = sheet.getSheetName();

    if (StringUtils.isBlank(sheetName)) {

      return new SheetBean();
    }

    return new SheetBean(sheetName);
  }

  private Row createRow() {
    return new RowBean();
  }

  private Cell createCell(org.apache.poi.ss.usermodel.Cell cell) {
    if (cell == null) {

      return new CellBean();
    }

    String value;

    int cellType = cell.getCellType();
    if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {

      value = null;

    } else if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN) {

      value = Boolean.toString(cell.getBooleanCellValue());

    } else if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR) {

      value = null;

    } else if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA) {

      value = null;

    } else if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {

      if (DateUtil.isCellDateFormatted(cell)) {

        value = cell.getDateCellValue().toString();

      } else {

        value = NumberToTextConverter.toText(cell.getNumericCellValue());

      }

    } else if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {

      String cellValue = cell.getStringCellValue();
      value = StringUtils.isBlank(cellValue) ? null : cellValue.trim();

    } else {

      value = null;

    }

    return new CellBean(value);

  }

  private int getMaxColNum(org.apache.poi.ss.usermodel.Sheet sheet) {
    int maxColNum = 0;
    for (int j = 0; j <= sheet.getLastRowNum(); j++) {
      org.apache.poi.ss.usermodel.Row row = sheet.getRow(j);
      maxColNum = Math.max(row.getLastCellNum(), maxColNum);
    }
    return maxColNum;
  }

}
