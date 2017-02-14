package com.supwisdom.spreadsheet.mapper.model.meta;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

/**
 * Created by hanwen on 2016/12/27.
 */
public class SheetMetaBean implements SheetMeta {

  private int sheetIndex = 1;

  private String sheetName;

  private int dataStartRowIndex;

  private WorkbookMeta workbookMeta;

  private Map<String, FieldMeta> name2FieldMeta = new HashMap<>();

  private Map<Integer, FieldMeta> column2FieldMeta = new HashMap<>();

  public SheetMetaBean(int dataStartRowIndex) {
    this(null, dataStartRowIndex);
  }

  public SheetMetaBean(String sheetName, int dataStartRowIndex) {
    this.sheetName = sheetName;
    this.dataStartRowIndex = dataStartRowIndex;
  }

  @Override
  public int getSheetIndex() {
    return sheetIndex;
  }

  @Override
  public String getSheetName() {
    return sheetName;
  }

  @Override
  public int getDataStartRowIndex() {
    return dataStartRowIndex;
  }

  @Override
  public List<FieldMeta> getFieldMetas() {
    List<FieldMeta> fieldMetas = new ArrayList<>(this.name2FieldMeta.values());
    Collections.sort(fieldMetas);
    return fieldMetas;
  }

  @Override
  public FieldMeta getFieldMeta(String fieldName) {
    if (name2FieldMeta.isEmpty()) {
      return null;
    }
    return name2FieldMeta.get(fieldName);
  }

  @Override
  public FieldMeta getFieldMeta(int columnIndex) {
    if (column2FieldMeta.isEmpty()) {
      return null;
    }
    return column2FieldMeta.get(columnIndex);
  }

  @Override
  public void removeFieldMeta(String fieldName) {
    if (name2FieldMeta.isEmpty()) {
      return;
    }
    FieldMeta fieldMeta = name2FieldMeta.remove(fieldName);
    if (fieldMeta != null) {
      column2FieldMeta.remove(fieldMeta.getColumnIndex());
    }

  }

  @Override
  public void addFieldMeta(FieldMeta fieldMeta) {
    if (fieldMeta == null) {
      throw new IllegalArgumentException("fieldMeta is null");
    }

    String fieldName = fieldMeta.getName();
    int columnIndex = fieldMeta.getColumnIndex();

    if (name2FieldMeta.containsKey(fieldName)) {
      throw new IllegalArgumentException("This sheetMeta already has FieldMeta for name [" + fieldName + "]");
    }
    if (column2FieldMeta.containsKey(columnIndex)) {
      throw new IllegalArgumentException("This sheetMeta already has FieldMeta for column [" + columnIndex + "]");
    }

    ((FieldMetaBean) fieldMeta).setSheetMeta(this);
    name2FieldMeta.put(fieldName, fieldMeta);
    column2FieldMeta.put(columnIndex, fieldMeta);

  }

  @Override
  public WorkbookMeta getWorkbookMeta() {
    return workbookMeta;
  }

  void setWorkbookMeta(WorkbookMeta workbookMeta) {
    this.workbookMeta = workbookMeta;
  }

  void setSheetIndex(int sheetIndex) {
    this.sheetIndex = sheetIndex;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("sheetName", sheetName)
        .append("dataStartRowIndex", dataStartRowIndex)
        .toString();
  }

}
