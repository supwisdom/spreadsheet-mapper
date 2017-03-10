package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.List;
import java.util.function.BiFunction;

/**
 * multi cell bifunction 参数
 * <p>
 * Created by hanwen on 2017/2/6.
 */
public class LambdaUnionCellValidatorParam {

  private BiFunction<List<Cell>, List<FieldMeta>, Boolean> lambda;

  public LambdaUnionCellValidatorParam(BiFunction<List<Cell>, List<FieldMeta>, Boolean> lambda) {
    this.lambda = lambda;
  }

  public BiFunction<List<Cell>, List<FieldMeta>, Boolean> getLambda() {
    return lambda;
  }
}
