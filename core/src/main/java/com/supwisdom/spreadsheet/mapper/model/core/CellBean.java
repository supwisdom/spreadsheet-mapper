package com.supwisdom.spreadsheet.mapper.model.core;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Objects;

/**
 * Created by hanwen on 15-12-16.
 */
public class CellBean implements Cell {

  private int index = -1;

  private String value;

  private transient Row row;

  private CellType cellType;

  public CellBean() {
    // default constructor, empty cell
  }

  public CellBean(String value) {
    this.value = value;
  }

  public CellBean(String value, CellType cellType) {
    this.value = value;
    this.cellType = cellType;
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
        Objects.equals(value, cell.value) &&
        Objects.equals(cellType, cell.cellType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, value, cellType);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("value", value)
        .append("index", index)
        .append("cellType", cellType)
        .toString();
  }

  void setRow(Row row) {
    this.row = row;
  }

  void setIndex(int index) {
    this.index = index;
  }

  @Override
  public CellType getCellType() {
    return cellType;
  }

  public void setCellType(CellType cellType) {
    this.cellType = cellType;
  }
}
