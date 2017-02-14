package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * field value converter adapter, easy implements customer value converter extends this.
 * <p>
 * Created by hanwen on 5/3/16.
 */
public abstract class FieldConverterAdapter<T, V extends FieldConverterAdapter<T, V>> implements FieldConverter<T> {

  private String matchField;

  public V matchField(String matchField) {
    this.matchField = matchField;
    return (V) this;
  }

  @Override
  public String getMatchField() {
    return matchField;
  }

  @Override
  public abstract String getValue(T object, Cell cell, FieldMeta fieldMeta);
}
