package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qianjia on 2017/3/14.
 */
public class ExecRecordRowProcessListener implements RowProcessListener {

  private final ExecutionRecorder executionRecorder;

  public ExecRecordRowProcessListener(ExecutionRecorder executionRecorder) {
    this.executionRecorder = executionRecorder;
  }

  @Override
  public void before(Object object, Row row, SheetMeta sheetMeta) {

    String execution =
        StringUtils.join("row-listener#before", "[", sheetMeta.getSheetName(), ",", row.getIndex(), "]");
    executionRecorder.record(execution);

  }

  @Override
  public void after(Object object, Row row, SheetMeta sheetMeta) {

    String execution =
        StringUtils.join("row-listener#after", "[", sheetMeta.getSheetName(), ",", row.getIndex(), "]");
    executionRecorder.record(execution);

  }
}
