package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.*;

/**
 * 限定值校验器。{@link Cell#getValue()}不允许超出限定值范围
 * Created by hanwen on 2017/1/11.
 */
public class ValueScopeValidator extends CellValidatorTemplate<ValueScopeValidator> {

  private Set<String> restrictValues = Collections.emptySet();

  /**
   * 限定的值范围
   *
   * @param restrictValues 限定值范围
   */
  public ValueScopeValidator(Collection<String> restrictValues) {
    this.restrictValues = new HashSet<>(restrictValues);
  }

  /**
   * 限定的值范围
   *
   * @param restrictValues 限定值范围
   */
  public ValueScopeValidator(String[] restrictValues) {
    this.restrictValues = new HashSet<>(Arrays.asList(restrictValues));
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {
    String value = cell.getValue();
    return restrictValues.contains(value);
  }

}
