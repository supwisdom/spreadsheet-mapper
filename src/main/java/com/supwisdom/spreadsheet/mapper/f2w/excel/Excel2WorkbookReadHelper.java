package com.supwisdom.spreadsheet.mapper.f2w.excel;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReadException;
import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReadHelper;
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
import java.text.SimpleDateFormat;

/**
 * excel to workbook reader decorator
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public class Excel2WorkbookReadHelper implements WorkbookReadHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(Excel2WorkbookReadHelper.class);

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
        String dateFormat = DateFormatRegisterer.GLOBAL.getCorrespondingFormat(cell.getCellStyle().getDataFormatString());

        if (dateFormat == null) {
          value = DateFormatRegisterer.ERROR_PATTERN;
        } else {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
          value = simpleDateFormat.format(cell.getDateCellValue());
        }
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
