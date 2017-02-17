package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import java.util.*;

public class ValueScopeParam {

  private Set<String> restrictValues = Collections.emptySet();

  /**
   * 限定的值范围
   *
   * @param restrictValues 限定值
   */
  public ValueScopeParam(Collection<String> restrictValues) {
    this.restrictValues = new HashSet<>(restrictValues);
  }

  /**
   * 限定的值范围
   *
   * @param restrictValues 限定值
   */
  public ValueScopeParam(String[] restrictValues) {
    this.restrictValues = new HashSet<>(Arrays.asList(restrictValues));
  }

  public Set<String> getRestrictValues() {
    return restrictValues;
  }

}
