package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * the boolean additional param
 * Created by hanwen on 2017/1/23.
 */
public class BooleanParam {

  private Set<String> trueStrings = new HashSet<>();

  private Set<String> falseStrings = new HashSet<>();

  public Set<String> getTrueStrings() {
    return trueStrings;
  }

  public Set<String> getFalseStrings() {
    return falseStrings;
  }

  public BooleanParam trueStrings(String... supportedTrue) {
    if (supportedTrue == null) {
      return this;
    }
    Collections.addAll(this.trueStrings, supportedTrue);
    return this;
  }

  public BooleanParam falseStrings(String... supportedFalse) {
    if (supportedFalse == null) {
      return this;
    }
    Collections.addAll(this.falseStrings, supportedFalse);
    return this;
  }
}
