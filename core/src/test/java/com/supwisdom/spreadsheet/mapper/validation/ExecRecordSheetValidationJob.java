package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.validation.validator.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

import java.util.List;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecRecordSheetValidationJob implements SheetValidationJob {

  private final String name;

  private final boolean returnResult;

  private final ExecutionRecorder executionRecorder;

  public ExecRecordSheetValidationJob(String name, boolean returnResult, ExecutionRecorder executionRecorder) {
    this.name = name;
    this.returnResult = returnResult;
    this.executionRecorder = executionRecorder;
  }

  @Override
  public SheetValidationJob addValidator(SheetValidator sheetValidator) {
    return null;
  }

  @Override
  public SheetValidationJob addValidator(RowValidator rowValidator) {
    return null;
  }

  @Override
  public SheetValidationJob addValidator(CellValidator cellValidator) {
    return null;
  }

  @Override
  public SheetValidationJob addValidator(UnionCellValidator unionCellValidator) {
    return null;
  }

  @Override
  public boolean validate(Sheet sheet, SheetMeta sheetMeta) {
    executionRecorder.record(name + "#validate");
    return returnResult;
  }

  @Override
  public List<Message> getErrorMessages() {
    return null;
  }

}
