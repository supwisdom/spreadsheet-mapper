package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * required validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class RequireValidator implements SingleCellValidator {

  private String group;

  private LinkedHashSet<String> dependsOn = new LinkedHashSet<>();

  private String matchField;

  private String errorMessage;

  final public RequireValidator matchField(String matchField) {
    this.matchField = matchField;
    return this;
  }

  final public RequireValidator errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  final public RequireValidator dependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return this;
    }
    Collections.addAll(this.dependsOn, dependsOn);
    return this;
  }

  final public RequireValidator group(String group) {
    this.group = group;
    return this;
  }

  @Override
  final public boolean validate(Cell cell, FieldMeta fieldMeta) {
    return StringUtils.isNotBlank(cell.getValue());
  }

  @Override
  final public String getGroup() {
    return group;
  }

  @Override
  final public String getMatchField() {
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

}
