package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.validation.builder.CellValidatorBatchBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CellBuildUnit {

  protected CellValidatorBatchBuilder cellValidatorBatchBuilder;

  protected CellValidatorFactory factory;

  protected List<String> matchFields = new ArrayList<>();

  protected String group;

  protected List<String> dependsOn = new ArrayList<>(2);

  protected String errorMessage;

  protected Object additionalParam;

  public CellBuildUnit(CellValidatorBatchBuilder cellValidatorBatchBuilder, CellValidatorFactory factory) {
    this.cellValidatorBatchBuilder = cellValidatorBatchBuilder;
    this.factory = factory;
  }

  public CellBuildUnit matchFields(String... matchFields) {
    if (matchFields == null) {
      return this;
    }
    Collections.addAll(this.matchFields, matchFields);
    return this;
  }

  public CellBuildUnit group(String group) {
    this.group = group;
    return this;
  }

  public CellBuildUnit dependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return this;
    }
    this.dependsOn.addAll(Arrays.asList(dependsOn));
    return this;
  }

  public CellBuildUnit errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public CellBuildUnit param(Object additionalParam) {
    this.additionalParam = additionalParam;
    return this;
  }

  public CellValidatorBatchBuilder end() {

    for (CellBuildUnitParam cellBuildUnitParam : createBuildUnitParams()) {
      cellValidatorBatchBuilder.addCellValidator(factory.create(cellBuildUnitParam));
    }
    return cellValidatorBatchBuilder;
  }

  private List<CellBuildUnitParam> createBuildUnitParams() {

    List<CellBuildUnitParam> params = new ArrayList<>(this.matchFields.size());
    for (String matchField : matchFields) {

      CellBuildUnitParam newParam = new CellBuildUnitParam();

      if (StringUtils.isBlank(this.group)) {
        newParam.setGroup(matchField);
      } else {
        newParam.setGroup(this.group);
      }

      newParam.setMatchField(matchField);
      newParam.getDependsOn().addAll(this.dependsOn);
      newParam.setErrorMessage(matchField);
      newParam.setAdditionalParam(this.additionalParam);
      newParam.setErrorMessage(this.errorMessage);

      params.add(newParam);
    }

    return params;

  }
}
