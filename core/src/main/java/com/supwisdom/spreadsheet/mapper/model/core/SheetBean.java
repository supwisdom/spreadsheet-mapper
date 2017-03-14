package com.supwisdom.spreadsheet.mapper.model.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 15-12-16.
 */
public class SheetBean implements Sheet {

  private int index = 1;

  private String name;

  private List<Row> rows = new ArrayList<>();

  private transient Workbook workbook;

  public SheetBean() {
    // default constructor
  }

  public SheetBean(String name) {
    this.name = name;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<Row> getRows() {
    return rows;
  }

  @Override
  public int sizeOfRows() {
    return getRows().size();
  }

  @Override
  public Row getRow(int rowIndex) {
    if (rowIndex < 1 || rowIndex > sizeOfRows()) {
      throw new IllegalArgumentException("row index out of bounds");
    }
    if (sizeOfRows() == 0) {
      return null;
    }
    return rows.get(rowIndex - 1);
  }

  @Override
  public boolean addRow(Row row) {
    ((RowBean) row).setSheet(this);
    ((RowBean) row).setIndex(sizeOfRows() + 1);
    return rows.add(row);
  }

  @Override
  public Row getFirstRow() {
    return getRow(1);
  }

  @Override
  public Workbook getWorkbook() {
    return workbook;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("index", index)
        .append("name", name)
        .toString();
  }

  public void setWorkbook(Workbook workbook) {
    this.workbook = workbook;
  }

  void setIndex(int index) {
    this.index = index;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SheetBean sheetBean = (SheetBean) o;

    if (index != sheetBean.index) return false;
    if (name != null ? !name.equals(sheetBean.name) : sheetBean.name != null) return false;
    return rows != null ? rows.equals(sheetBean.rows) : sheetBean.rows == null;
  }

  @Override
  public int hashCode() {
    int result = index;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (rows != null ? rows.hashCode() : 0);
    return result;
  }
}
