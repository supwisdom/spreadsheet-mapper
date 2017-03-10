package com.supwisdom.spreadsheet.mapper.model.shapes;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by hanwen on 15-12-17.
 */
public class CommentBean implements Comment {

  private String message;

  private int sheetIndex;

  private int rowIndex;

  private int columnIndex;

  private int length;

  private int height;

  public CommentBean(String message, int sheetIndex, int rowIndex, int columnIndex) {
    this(message, sheetIndex, rowIndex, columnIndex, 3, 1);
  }

  public CommentBean(String message, int sheetIndex, int rowIndex, int columnIndex, int length, int height) {
    this.message = message;
    this.sheetIndex = sheetIndex;
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.length = length;
    this.height = height;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getLength() {
    return length;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getSheetIndex() {
    return sheetIndex;
  }

  @Override
  public int getRowIndex() {
    return rowIndex;
  }

  @Override
  public int getColumnIndex() {
    return columnIndex;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("message", message)
        .append("sheetIndex", sheetIndex)
        .append("rowIndex", rowIndex)
        .append("columnIndex", columnIndex)
        .append("length", length)
        .append("height", height)
        .toString();
  }
}
