package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

import java.util.LinkedHashSet;

/**
 * <pre>
 * dependency validator, after workbook and sheet and row validators, if post validators failure, dependency validators will skip.
 * dependency validator will hit on each rows.
 * notice:
 * 1. each rows validate result is isolated.
 * 2. when valid one row the other rows validate result not influence this row valid.
 * 3. only hit on data rows {@link SheetMeta#getDataStartRowIndex()}.
 * 4. all validators hit sequence (if no dependency) is validator add to helper sequence.
 * 5. the same group validators hit sequence is validator add to helper sequence.
 * </pre>
 * Created by hanwen on 2016/12/26.
 */
public interface Validator {

  /**
   * the validator group name
   *
   * @return the group name
   */
  String getGroup();

  /**
   * <pre>
   * the validator do valid after depends on group validators.
   * the group validators sequence is the sequence of add to validate engine.
   * notice:
   * 1. all depends on groups valid passed will do this validator.
   * 2. if in the same groups one valid failure the rest validators of this group will skip.
   * 3. if siblings groups, one group failure, others siblings groups if do valid depends on the sequence of depends on groups you add,
   *    if the failure group add first, other siblings groups will skip, else other siblings group will do valid.
   * </pre>
   *
   * @return depends on groups
   */
  LinkedHashSet<String> getDependsOn();
}
