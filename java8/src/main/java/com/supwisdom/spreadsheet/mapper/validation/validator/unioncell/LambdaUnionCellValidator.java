package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaUnionCellValidator extends CustomUnionCellValidator<LambdaUnionCellValidator> {

  private BiFunction<List<Cell>, List<FieldMeta>, Boolean> biFunction;

  /**
   * @param biFunction Boolean function(List&lt;Cell&gt;, List&lt;FieldMeta&gt;)
   */
  public LambdaUnionCellValidator(BiFunction<List<Cell>, List<FieldMeta>, Boolean> biFunction) {
    this.biFunction = biFunction;
  }

  @Override
  protected boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas) {
    return biFunction.apply(cells, fieldMetas);
  }

}

