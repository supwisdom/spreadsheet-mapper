package com.supwisdom.spreadsheet.mapper.validation.builder;

/**
 * the number scale range additional param
 * Created by hanwen on 2017/1/23.
 */
public class NumberScaleRangeParam {

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
