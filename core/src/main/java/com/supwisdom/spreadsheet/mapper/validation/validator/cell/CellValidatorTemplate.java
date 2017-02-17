package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * 自定义{@link CellValidator}的抽象类，只需实现{@link #doValidate(Cell, FieldMeta)}即可。
 * 需要注意的是，当{@link Cell#getValue()}是{@link StringUtils#isBlank(CharSequence)}的时候，会认为验证通过。
 * Created by hanwen on 2017/1/11.
 */
public abstract class CellValidatorTemplate<V extends CellValidatorTemplate<V>> implements CellValidator {

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
   * 子类override此方法做验证逻辑
   *
   * @param cell      {@link Cell} 传入的{@link Cell#getValue()}是{@link StringUtils#isNotBlank(CharSequence)}的
   * @param fieldMeta {@link FieldMeta}
   * @return true if pass
   */
  protected abstract boolean doValidate(Cell cell, FieldMeta fieldMeta);

}
