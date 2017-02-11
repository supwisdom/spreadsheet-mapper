package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellBuildUnitParam {

  protected String matchField;

  protected String group;

  protected List<String> dependsOn = new ArrayList<>(2);

  protected String errorMessage;

  protected Object additionalParam;

  public void setMatchField(String matchField) {
    this.matchField = matchField;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public void addDependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return;
    }
    this.dependsOn.addAll(Arrays.asList(dependsOn));
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setAdditionalParam(Object additionalParam) {
    this.additionalParam = additionalParam;
  }

  public String getMatchField() {
    return matchField;
  }

  public String getGroup() {
    return group;
  }

  public List<String> getDependsOn() {
    return dependsOn;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Object getAdditionalParam() {
    return additionalParam;
  }

}
