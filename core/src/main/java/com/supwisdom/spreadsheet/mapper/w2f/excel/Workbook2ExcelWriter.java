package com.supwisdom.spreadsheet.mapper.w2f.excel;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.w2f.WorkbookWriteException;
import com.supwisdom.spreadsheet.mapper.w2f.WorkbookWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将WorkBook写到Excel的工具
 * Created by hanwen on 2016/12/30.
 */
public class Workbook2ExcelWriter implements WorkbookWriter {

  private static final Logger LOGGER = LoggerFactory.getLogger(Workbook2ExcelWriter.class);

  private static final String EMPTY_VALUE = "";

  private static final Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9]+.?[0-9]+");

  private org.apache.poi.ss.usermodel.Workbook poiWorkbook;

  /**
   * default xlsx is true
   *
   * @see #Workbook2ExcelWriter(boolean)
   */
  public Workbook2ExcelWriter() {
    this(true);
  }

  /**
   * 写出到.xls或.xlsx文件
   *
   * @param xlsx true代表写出到.xlsx文件，false代表写出到xls文件
   */
  public Workbook2ExcelWriter(boolean xlsx) {
    // sxssf keep 100 rows in memory, exceeding rows will be flushed to disk
    poiWorkbook = xlsx ? new SXSSFWorkbook(100) : new HSSFWorkbook();
  }

  public void write(Workbook workbook, OutputStream outputStream) {
    if (workbook == null) {
      return;
    }

    for (Sheet excelSheet : workbook.getSheets()) {

      org.apache.poi.ss.usermodel.Sheet sheet = createSheet(poiWorkbook, excelSheet);

      for (Row excelRow : excelSheet.getRows()) {

        org.apache.poi.ss.usermodel.Row row = createRow(sheet, excelRow);

        for (Cell excelCell : excelRow.getCells()) {

          setSuitWidth(excelCell, excelRow, sheet);

          createCell(row, excelCell, excelRow);
        }
      }
    }

    try {
      poiWorkbook.write(outputStream);

      if (poiWorkbook instanceof SXSSFWorkbook) {
        ((SXSSFWorkbook) poiWorkbook).dispose();
      }
    } catch (IOException e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookWriteException(e);
    } finally {

      try {
        poiWorkbook.close();
      } catch (IOException e) {
        LOGGER.error(ExceptionUtils.getStackTrace(e));
      }
    }

  }

  private void setSuitWidth(Cell excelCell, Row excelRow, org.apache.poi.ss.usermodel.Sheet sheet) {

    int rowIndex = excelRow.getIndex();
    if (excelCell.getValue() == null || (rowIndex != 1 && rowIndex != 2)) {
      return;
    }
    // 合适的宽度
    int suitWidth = excelCell.getValue().getBytes().length * 256;
    suitWidth = suitWidth > 65280 ? 65280 : suitWidth;
    int columnIndex = excelCell.getIndex() - 1;

    if (rowIndex == 1 && excelCell.getValue() != null) {
      sheet.setColumnWidth(columnIndex, suitWidth);
    }
    if (rowIndex == 2 && excelCell.getValue() != null && suitWidth > sheet.getColumnWidth(columnIndex)) {
      sheet.setColumnWidth(columnIndex, suitWidth);
    }
  }

  private org.apache.poi.ss.usermodel.Sheet createSheet(org.apache.poi.ss.usermodel.Workbook workbook, Sheet sheet) {
    String sheetName = sheet.getName();

    org.apache.poi.ss.usermodel.Sheet poiSheet;
    if (StringUtils.isBlank(sheetName)) {
      poiSheet = workbook.createSheet();
    } else {
      poiSheet = workbook.createSheet(sheetName);
    }

    return poiSheet;
  }

  private org.apache.poi.ss.usermodel.Row createRow(org.apache.poi.ss.usermodel.Sheet sheet, Row excelRow) {
    return sheet.createRow(excelRow.getIndex() - 1);
  }

  /**
   * 对于cellType的调整
   * 如果内容是数值类型，并且要求cellType是数值类型，则转换内容为数值型
   *
   * @param row
   * @param excelCell
   * @param excelRow
   */
  private void createCell(org.apache.poi.ss.usermodel.Row row, Cell excelCell, Row excelRow) {
    String value = excelCell.getValue();
    CellType cellType = excelCell.getCellType();
    org.apache.poi.ss.usermodel.Cell cell = row.createCell(excelCell.getIndex() - 1, cellType != null ? cellType : CellType.STRING);
    if (value == null) {
      cell.setCellValue(EMPTY_VALUE);
    } else if (CellType.NUMERIC.equals(cellType)) {
      Matcher isNum = NUMBER_PATTERN.matcher(value);
      if (isNum.matches()) {
        cell.setCellValue(NumberUtils.createDouble(value));
      } else {
        cell.setCellValue(value);
      }
    } else {
      cell.setCellValue(value);
    }


    if (excelRow.getIndex() == 3 && value != null && value.contains("必填")) {
      CellStyle cellStyle = poiWorkbook.createCellStyle();
      cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
      cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      cell.setCellStyle(cellStyle);
    }
  }

}
