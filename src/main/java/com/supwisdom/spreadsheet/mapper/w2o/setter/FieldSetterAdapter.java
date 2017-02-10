package com.supwisdom.spreadsheet.mapper.w2o.setter;


import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * <pre>
 * field value setter adapter, easy implements customer value setter extends this.
 * extends this will skip custom set when cell value is blank (default blank value means no need set ).
 * </pre>
 * Created by hanwen on 15-12-16.
 */
public abstract class FieldSetterAdapter<T, V extends FieldSetterAdapter<T, V>> implements FieldSetter<T> {

  private String matchField;

  public V matchField(String matchField) {
    this.matchField = matchField;
    return getThis();
  }

  @Override
  public String getMatchField() {
    return matchField;
  }

  @Override
  public void set(T object, Cell cell, FieldMeta fieldMeta) {
    if (StringUtils.isBlank(cell.getValue())) {
      return;
    }
    customSet(object, cell, fieldMeta);
  }

  protected abstract V getThis();

  protected abstract void customSet(T object, Cell cell, FieldMeta fieldMeta);
}
