package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnionCellBuildUnitParam {

  protected List<String> matchFields = new ArrayList<>();

  protected String group;

  protected List<String> dependsOn = new ArrayList<>(2);

  protected String errorMessage;

  protected Object additionalParam;

  public void addMatchFields(String... matchFields) {
    if (matchFields == null) {
      return;
    }
    Collections.addAll(this.matchFields, matchFields);
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

  public List<String> getMatchFields() {
    return matchFields;
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
