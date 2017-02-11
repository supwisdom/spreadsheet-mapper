package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * <pre>
 * cell value validator adapter, easy implements customer cell validator extends this.
 * extends this will skip custom validate when cell value is blank (default blank value means no need validate).
 * </pre>
 * Created by hanwen on 2017/1/11.
 */
public abstract class CustomCellValidator<V extends CustomCellValidator<V>> implements CellValidator {

  private String group;

  private LinkedHashSet<String> dependsOn = new LinkedHashSet<>();

  private String matchField;

  private String errorMessage;

  final public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  final public V errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return (V) this;
  }

  final public V dependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return (V) this;
    }
    Collections.addAll(this.dependsOn, dependsOn);
    return (V) this;
  }

  final public V group(String group) {
    this.group = group;
    return (V) this;
  }

  @Override
  public boolean validate(Cell cell, FieldMeta fieldMeta) {

    return StringUtils.isBlank(cell.getValue()) || doValidate(cell, fieldMeta);
  }

  @Override
  final public String getGroup() {
    return group;
  }

  @Override
  public String getMatchField() {
    return matchField;
  }

  @Override
  final public LinkedHashSet<String> getDependsOn() {
    return dependsOn;
  }

  @Override
  final public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * for customer implements validate
   *
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   * @return true if pass
   */
  protected abstract boolean doValidate(Cell cell, FieldMeta fieldMeta);

}
