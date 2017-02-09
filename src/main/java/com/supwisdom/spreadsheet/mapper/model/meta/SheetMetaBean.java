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

  private Map<String, FieldMeta> fieldMetas = new HashMap<>();

  private WorkbookMeta workbookMeta;

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
    List<FieldMeta> fieldMetas = new ArrayList<>(this.fieldMetas.values());
    Collections.sort(fieldMetas);
    return fieldMetas;
  }

  @Override
  public FieldMeta getFieldMeta(String fieldName) {
    if (fieldMetas.isEmpty()) {
      return null;
    }
    return fieldMetas.get(fieldName);
  }

  @Override
  public void removeFieldMeta(String fieldName) {
    if (fieldMetas.isEmpty()) {
      return;
    }
    fieldMetas.remove(fieldName);
  }

  @Override
  public void addFieldMeta(FieldMeta fieldMeta) {
    if (fieldMeta == null) {
      throw new IllegalArgumentException("field meta can not be null");
    }
    String fieldName = fieldMeta.getName();
    if (fieldMetas.containsKey(fieldName)) {
      throw new IllegalArgumentException("this sheet meta contains multi field meta[" + fieldName + "]");
    }

    ((FieldMetaBean) fieldMeta).setSheetMeta(this);
    fieldMetas.put(fieldName, fieldMeta);
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
