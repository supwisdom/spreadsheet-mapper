package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by qianjia on 2017/3/14.
 */
public class ExecRecordSheetProcessListener implements SheetProcessListener {

  private final ExecutionRecorder executionRecorder;

  public ExecRecordSheetProcessListener(ExecutionRecorder executionRecorder) {
    this.executionRecorder = executionRecorder;
  }

  @Override
  public void before(Sheet sheet, SheetMeta sheetMeta) {

    String execution = StringUtils.join("sheet-listener#before", "[", sheetMeta.getSheetName(), "]");
    executionRecorder.record(execution);

  }

  @Override
  public void after(List objects, Sheet sheet, SheetMeta sheetMeta) {

    String execution = StringUtils.join("sheet-listener#after", "[", sheetMeta.getSheetName(), "]");
    executionRecorder.record(execution);

  }

}
