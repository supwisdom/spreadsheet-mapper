package com.supwisdom.spreadsheet.mapper.w2o.param;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * the boolean additional param
 * <p>
 * Created by hanwen on 2017/1/23.
 */
public class BooleanParam implements AdditionalParam {

  private Set<String> supportedTrue = new HashSet<>();

  private Set<String> supportedFalse = new HashSet<>();

  public Set<String> getSupportedTrue() {
    return supportedTrue;
  }

  public Set<String> getSupportedFalse() {
    return supportedFalse;
  }

  public BooleanParam supportedTrue(String... supportedTrue) {
    if (supportedTrue == null) {
      return this;
    }
    Collections.addAll(this.supportedTrue, supportedTrue);
    return this;
  }

  public BooleanParam supportedFalse(String... supportedFalse) {
    if (supportedFalse == null) {
      return this;
    }
    Collections.addAll(this.supportedFalse, supportedFalse);
    return this;
  }
}
