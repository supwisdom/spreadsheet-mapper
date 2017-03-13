package com.supwisdom.spreadsheet.mapper.validation.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianjia on 2017/3/13.
 */
public class ExecutionRecorder {

  private List<String> executions = new ArrayList<>();

  public void record(String execution) {
    this.executions.add(execution);
  }

  public List<String> getExecutions() {
    return new ArrayList<>(executions);
  }
}
