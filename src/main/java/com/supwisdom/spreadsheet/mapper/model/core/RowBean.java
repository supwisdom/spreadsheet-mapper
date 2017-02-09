package com.supwisdom.spreadsheet.mapper.model.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 15-12-16.
 */
public class RowBean implements Row {

  private int index = 1;

  private List<Cell> cells = new ArrayList<>();

  private Sheet sheet;

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public List<Cell> getCells() {
    return cells;
  }

  @Override
  public int sizeOfCells() {
    return cells.size();
  }

  @Override
  public Cell getCell(int columnIndex) {
    if (columnIndex < 1 || columnIndex > sizeOfCells()) {
      throw new IllegalArgumentException("column index out of bounds");
    }
    return cells.get(columnIndex - 1);
  }

  @Override
  public boolean addCell(Cell cell) {
    ((CellBean) cell).setRow(this);
    ((CellBean) cell).setIndex(sizeOfCells() + 1);
    return cells.add(cell);
  }

  @Override
  public Cell getFirstCell() {
    if (sizeOfCells() == 0) {
      return null;
    }
    return getCell(1);
  }

  @Override
  public Sheet getSheet() {
    return sheet;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("index", index)
        .toString();
  }

  void setSheet(Sheet sheet) {
    this.sheet = sheet;
  }

  void setIndex(int index) {
    this.index = index;
  }
}
