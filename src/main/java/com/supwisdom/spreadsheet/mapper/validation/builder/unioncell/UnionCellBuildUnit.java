package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.validation.builder.CellValidatorBatchBuilder;

public class UnionCellBuildUnit {

  protected CellValidatorBatchBuilder batchBuilder;

  protected UnionCellValidatorFactory factory;

  protected UnionCellBuildUnitParam unionCellBuildUnitParam = new UnionCellBuildUnitParam();

  public UnionCellBuildUnit(CellValidatorBatchBuilder batchBuilder, UnionCellValidatorFactory factory) {
    this.batchBuilder = batchBuilder;
    this.factory = factory;
  }

  public UnionCellBuildUnit matchFields(String... matchFields) {
    unionCellBuildUnitParam.addMatchFields(matchFields);
    return this;
  }

  public UnionCellBuildUnit group(String group) {
    unionCellBuildUnitParam.setGroup(group);
    return this;
  }

  public UnionCellBuildUnit dependsOn(String... dependsOn) {
    unionCellBuildUnitParam.addDependsOn(dependsOn);
    return this;
  }

  public UnionCellBuildUnit errorMessage(String errorMessage) {
    unionCellBuildUnitParam.setErrorMessage(errorMessage);
    return this;
  }

  public UnionCellBuildUnit param(Object additionalParam) {
    unionCellBuildUnitParam.setAdditionalParam(additionalParam);
    return this;
  }

  public CellValidatorBatchBuilder end() {

    batchBuilder.addUnionCellValidator(factory.create(unionCellBuildUnitParam));

    return batchBuilder;
  }

}
