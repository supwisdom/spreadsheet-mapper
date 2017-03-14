package com.supwisdom.spreadsheet.mapper.w2o.listener;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qianjia on 2017/3/14.
 */
public class ExecRecordCellProcessListener implements CellProcessListener {

  private final ExecutionRecorder executionRecorder;

  public ExecRecordCellProcessListener(ExecutionRecorder executionRecorder) {
    this.executionRecorder = executionRecorder;
  }

  @Override
  public void before(Object object, Cell cell, FieldMeta fieldMeta) {

    String execution =
        StringUtils.join("cell-listener#before", "[", fieldMeta.getName(), ",", cell.getRow().getIndex(), ",", cell.getIndex(), "]");
    executionRecorder.record(execution);

  }

  @Override
  public void after(Object object, Cell cell, FieldMeta fieldMeta) {

    String execution =
        StringUtils.join("cell-listener#after", "[", fieldMeta.getName(), ",", cell.getRow().getIndex(), ",", cell.getIndex(), "]");
    executionRecorder.record(execution);

  }

}
