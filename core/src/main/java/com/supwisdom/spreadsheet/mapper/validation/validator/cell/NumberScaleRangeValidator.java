package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 判断数字的小数点位数在指定范围内的校验器
 * Created by hanwen on 2017/1/11.
 */
public class NumberScaleRangeValidator extends CellValidatorTemplate<NumberScaleRangeValidator> {

  private final int gte;

  private final int lte;

  /**
   * @param gte 最小小数点位数（包含）
   * @param lte 最大小数点位数（包含）
   */
  public NumberScaleRangeValidator(int gte, int lte) {
    this.gte = gte;
    this.lte = lte;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    String value = cell.getValue();

    if (!NumberUtils.isParsable(value)) {
      return false;
    }

    String[] numberPlace = value.split("\\.");

    int scale = 0;
    if (numberPlace.length != 1) {
      scale = numberPlace[1].length();
    }

    return gte <= scale && scale <= lte;
  }
}
