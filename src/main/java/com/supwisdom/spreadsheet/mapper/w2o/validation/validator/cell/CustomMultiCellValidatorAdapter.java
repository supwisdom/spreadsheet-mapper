package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell;

import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * <pre>
 * multi cell value validator adapter, easy implements customer multi cell validator extends this.
 * extends this will skip custom valid when multi cell value is all blank, like {@link CustomSingleCellValidatorAdapter#valid(Cell, FieldMeta)}
 * </pre>
 * Created by hanwen on 2017/1/20.
 */
public abstract class CustomMultiCellValidatorAdapter<V extends CustomMultiCellValidatorAdapter<V>> implements MultiCellValidator {

  private String errorMessage;

  private LinkedHashSet<String> matchFields = new LinkedHashSet<>();

  private LinkedHashSet<String> dependsOn = new LinkedHashSet<>();

  private String group;

  public V errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return getThis();
  }

  public V matchFields(String... matchFields) {
    if (matchFields == null) {
      return getThis();
    }
    Collections.addAll(this.matchFields, matchFields);
    return getThis();
  }

  public V dependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return getThis();
    }
    Collections.addAll(this.dependsOn, dependsOn);
    return getThis();
  }

  public V group(String group) {
    this.group = group;
    return getThis();
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public boolean valid(List<Cell> cells, List<FieldMeta> fieldMetas) {

    boolean allBlank = true;

    for (Cell cell : cells) {
      if (StringUtils.isNotBlank(cell.getValue())) {
        allBlank = false;
        break;
      }
    }

    return allBlank || customValid(cells, fieldMetas);
  }

  @Override
  public LinkedHashSet<String> getMatchFields() {
    return matchFields;
  }

  @Override
  public String getGroup() {
    return group;
  }

  @Override
  public LinkedHashSet<String> getDependsOn() {
    return dependsOn;
  }

  /**
   * for customer implements valid.
   *
   * @param cells      {@link Cell}
   * @param fieldMetas {@link FieldMeta}
   * @return true if pass
   */
  protected abstract boolean customValid(List<Cell> cells, List<FieldMeta> fieldMetas);

  protected abstract V getThis();
}
