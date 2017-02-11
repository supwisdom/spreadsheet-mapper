package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.SheetValidationJob;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.CellBuildUnit;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.CellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.UnionCellBuildUnit;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.UnionCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/20.
 */
public class CellValidatorBatchBuilder {

  protected List<Object> cellValidators = new ArrayList<>();

  public CellBuildUnit start(CellValidatorFactory factory) {
    return new CellBuildUnit(this, factory);
  }

  public UnionCellBuildUnit start(UnionCellValidatorFactory factory) {
    return new UnionCellBuildUnit(this, factory);
  }

  public void addCellValidator(CellValidator cellValidator) {
    this.cellValidators.add(cellValidator);
  }

  public void addUnionCellValidator(UnionCellValidator unionCellValidator) {
    this.cellValidators.add(unionCellValidator);
  }

  public void addToSheetValidationJob(SheetValidationJob sheetValidationJob) {

    for (Object cellValidator : cellValidators) {

      if (CellValidator.class.isAssignableFrom(cellValidator.getClass())) {

        sheetValidationJob.addCellValidator((CellValidator) cellValidator);

      } else if (UnionCellValidator.class.isAssignableFrom(cellValidator.getClass())) {

        sheetValidationJob.addUnionCellValidator((UnionCellValidator) cellValidator);

      }

    }

  }

  public List getCellValidators() {
    return this.cellValidators;
  }

}
