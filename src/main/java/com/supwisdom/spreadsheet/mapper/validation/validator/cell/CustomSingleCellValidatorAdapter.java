package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * <pre>
 * cell value validator adapter, easy implements customer cell validator extends this.
 * extends this will skip custom valid when cell value is blank (default blank value means no need valid).
 * </pre>
 * Created by hanwen on 2017/1/11.
 */
public abstract class CustomSingleCellValidatorAdapter<V extends CustomSingleCellValidatorAdapter<V>> implements SingleCellValidator {

  private String group;

  private LinkedHashSet<String> dependsOn = new LinkedHashSet<>();

  private String matchField;

  private String errorMessage;

  public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  public V errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return (V) this;
  }

  public V dependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return (V) this;
    }
    Collections.addAll(this.dependsOn, dependsOn);
    return (V) this;
  }

  public V group(String group) {
    this.group = group;
    return (V) this;
  }

  @Override
  public boolean valid(Cell cell, FieldMeta fieldMeta) {

    return StringUtils.isBlank(cell.getValue()) || customValid(cell, fieldMeta);
  }

  @Override
  public String getGroup() {
    return group;
  }

  @Override
  public String getMatchField() {
    return matchField;
  }

  @Override
  public LinkedHashSet<String> getDependsOn() {
    return dependsOn;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * for customer implements valid
   *
   * @param cell      {@link Cell}
   * @param fieldMeta {@link FieldMeta}
   * @return true if pass
   */
  protected abstract boolean customValid(Cell cell, FieldMeta fieldMeta);

}
