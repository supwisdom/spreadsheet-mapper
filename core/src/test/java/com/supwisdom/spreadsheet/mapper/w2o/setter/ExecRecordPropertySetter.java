package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qianjia on 2017/3/14.
 */
public class ExecRecordPropertySetter extends PropertySetterTemplate {

  private final ExecutionRecorder executionRecorder;

  public ExecRecordPropertySetter(ExecutionRecorder executionRecorder) {
    this.executionRecorder = executionRecorder;
  }

  @Override
  public void setProperty(Object object, Cell cell, FieldMeta fieldMeta) {
    super.setProperty(object, cell, fieldMeta);
    String execution = StringUtils.join(
        "property-setter#setProperty",
        "[", fieldMeta.getName(), ",", cell.getRow().getIndex(), ",", cell.getIndex(), "]"
    );
    executionRecorder.record(execution);
  }

  @Override
  protected Object convertToProperty(String cellValue) {
    return cellValue;
  }

}
