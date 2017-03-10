package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 自定义{@link UnionCellValidator}的抽象类，只需实现{@link #doValidate(List, List)}即可。
 * 需要注意的是，当所有{@link Cell#getValue()}是{@link StringUtils#isBlank(CharSequence)}的时候，会认为验证通过。
 * Created by hanwen on 2017/1/20.
 */
public abstract class UnionCellValidatorTemplate<V extends UnionCellValidatorTemplate<V>> implements UnionCellValidator {

  private String errorMessage;

  private LinkedHashSet<String> matchFields = new LinkedHashSet<>();

  private LinkedHashSet<String> dependsOn = new LinkedHashSet<>();

  private String group;

  final public V errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return (V) this;
  }

  final public V matchFields(String... matchFields) {
    if (matchFields == null) {
      return (V) this;
    }
    Collections.addAll(this.matchFields, matchFields);
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
  final public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public boolean validate(List<Cell> cells, List<FieldMeta> fieldMetas) {

    boolean allBlank = true;

    for (Cell cell : cells) {
      if (StringUtils.isNotBlank(cell.getValue())) {
        allBlank = false;
        break;
      }
    }

    return allBlank || doValidate(cells, fieldMetas);
  }

  @Override
  public LinkedHashSet<String> getMatchFields() {
    return matchFields;
  }

  @Override
  final public String getGroup() {
    return group;
  }

  @Override
  final public LinkedHashSet<String> getDependsOn() {
    return dependsOn;
  }

  /**
   * for customer implements validate.
   *
   * @param cells      {@link Cell}
   * @param fieldMetas {@link FieldMeta}
   * @return true if pass
   */
  protected abstract boolean doValidate(List<Cell> cells, List<FieldMeta> fieldMetas);

}
