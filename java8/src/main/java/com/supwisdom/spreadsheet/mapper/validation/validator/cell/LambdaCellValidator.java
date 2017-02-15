package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.function.BiFunction;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaCellValidator extends CustomCellValidator<LambdaCellValidator> {

  private BiFunction<Cell, FieldMeta, Boolean> biFunction;

  /**
   * @param biFunction Boolean function(Cell, FieldMeta)
   */
  public LambdaCellValidator(BiFunction<Cell, FieldMeta, Boolean> biFunction) {
    this.biFunction = biFunction;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    return biFunction.apply(cell, fieldMeta);
  }

}
