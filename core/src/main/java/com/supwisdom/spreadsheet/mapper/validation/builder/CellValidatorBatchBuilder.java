package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.SheetValidationJob;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.CellBuildUnit;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.CellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.UnionCellBuildUnit;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.UnionCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link CellValidator} {@link UnionCellValidator}批量Builder
 * Created by hanwen on 2017/1/20.
 */
public class CellValidatorBatchBuilder {

  protected List<Dependant> cellValidators = new ArrayList<>();

  public CellBuildUnit start(CellValidatorFactory factory) {
    return new CellBuildUnit(this, factory);
  }

  public UnionCellBuildUnit start(UnionCellValidatorFactory factory) {
    return new UnionCellBuildUnit(this, factory);
  }

  public void addValidator(CellValidator cellValidator) {
    this.cellValidators.add(cellValidator);
  }

  public void addValidator(UnionCellValidator unionCellValidator) {
    this.cellValidators.add(unionCellValidator);
  }

  /**
   * 添加{@link SheetValidationJob}
   *
   * @param sheetValidationJob {@link SheetValidationJob}
   */
  public void addToSheetValidationJob(SheetValidationJob sheetValidationJob) {

    for (Dependant cellValidator : cellValidators) {

      if (CellValidator.class.isAssignableFrom(cellValidator.getClass())) {

        sheetValidationJob.addValidator((CellValidator) cellValidator);

      } else if (UnionCellValidator.class.isAssignableFrom(cellValidator.getClass())) {

        sheetValidationJob.addValidator((UnionCellValidator) cellValidator);

      } else {

        throw new RuntimeException("Not supported dependant: " + cellValidator.getClass().getName());
        
      }
    }

  }

  public List<Dependant> build() {
    return this.cellValidators;
  }

}
