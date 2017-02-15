package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.function.BiFunction;

/**
 * single cell bifunction 参数
 * Created by hanwen on 2017/1/23.
 */
public class LambdaCellValidatorParam {

  private BiFunction<Cell, FieldMeta, Boolean> lambda;

  public LambdaCellValidatorParam(BiFunction<Cell, FieldMeta, Boolean> lambda) {
    this.lambda = lambda;
  }

  public BiFunction<Cell, FieldMeta, Boolean> getLambda() {
    return lambda;
  }
}
