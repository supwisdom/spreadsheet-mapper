package com.supwisdom.spreadsheet.mapper.w2o.setter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * field value setter adapter, easy implements customer value setter extends this.
 * extends this will skip custom setValue when cell value is blank (default blank value means no need setValue ).
 * </pre>
 * Created by hanwen on 15-12-16.
 */
public abstract class CustomFieldSetter<T, V extends CustomFieldSetter<T, V>> implements FieldSetter<T> {

  private String matchField;

  final public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  @Override
  final public String getMatchField() {
    return matchField;
  }

  @Override
  final public void setValue(T object, Cell cell, FieldMeta fieldMeta) {
    if (StringUtils.isBlank(cell.getValue())) {
      return;
    }
    doSetValue(object, cell, fieldMeta);
  }

  protected abstract void doSetValue(T object, Cell cell, FieldMeta fieldMeta);
}
