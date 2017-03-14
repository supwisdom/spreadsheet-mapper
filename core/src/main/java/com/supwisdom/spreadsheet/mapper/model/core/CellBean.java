package com.supwisdom.spreadsheet.mapper.model.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Created by hanwen on 15-12-16.
 */
public class CellBean implements Cell {

  private int index = -1;

  private String value;

  private transient Row row;

  public CellBean() {
    // default constructor, empty cell
  }

  public CellBean(String value) {
    this.value = value;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public Row getRow() {
    return row;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    CellBean cell = (CellBean) obj;
    return Objects.equals(index, cell.index) &&
        Objects.equals(value, cell.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("value", value)
        .append("index", index)
        .toString();
  }

  void setRow(Row row) {
    this.row = row;
  }

  void setIndex(int index) {
    this.index = index;
  }

}
