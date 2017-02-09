package com.supwisdom.spreadsheet.mapper.w2o.param;

/**
 * the number scale range additional param
 * <p>
 * Created by hanwen on 2017/1/23.
 */
public class NumberScaleRangeParam implements AdditionalParam {

  private int gte;

  private int lte;

  public int getGte() {
    return gte;
  }

  public int getLte() {
    return lte;
  }

  public NumberScaleRangeParam gte(int gte) {
    this.gte = gte;
    return this;
  }

  public NumberScaleRangeParam lte(int lte) {
    this.lte = lte;
    return this;
  }
}
