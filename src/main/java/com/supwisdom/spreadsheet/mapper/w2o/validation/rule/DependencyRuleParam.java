package com.supwisdom.spreadsheet.mapper.w2o.validation.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * the param dependency rule builder need
 * <p>
 * Created by hanwen on 2017/1/22.
 */
public class DependencyRuleParam {

  private String group;
  private List<String> dependsOn = new ArrayList<>();
  private String errorMessage;
  private Object additionalParam;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public List<String> getDependsOn() {
    return dependsOn;
  }

  public void setDependsOn(List<String> dependsOn) {
    this.dependsOn = dependsOn;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public Object getAdditionalParam() {
    return additionalParam;
  }

  public void setAdditionalParam(Object additionalParam) {
    this.additionalParam = additionalParam;
  }
}
