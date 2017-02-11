package com.supwisdom.spreadsheet.mapper.validation.validator;

import java.util.LinkedHashSet;

/**
 * Created by qianjia on 2017/2/11.
 */
public interface Dependant {

  /**
   * the validator group name
   *
   * @return the group name
   */
  String getGroup();

  /**
   * <pre>
   * the validator do validate after depends on group validators.
   * the group validators sequence is the sequence of add to validate engine.
   * notice:
   * 1. all depends on groups validate passed will do this validator.
   * 2. if in the same groups one validate failure the rest validators of this group will skip.
   * 3. if siblings groups, one group failure, others siblings groups if do validate depends on the sequence of depends on groups you add,
   *    if the failure group add first, other siblings groups will skip, else other siblings group will do validate.
   * </pre>
   *
   * @return depends on groups
   */
  LinkedHashSet<String> getDependsOn();

}
