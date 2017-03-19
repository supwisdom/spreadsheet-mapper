package com.supwisdom.spreadsheet.mapper.model.meta;

import org.apache.commons.collections.CollectionUtils;
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

  private Map<Integer, FieldMeta> column2FieldMeta = new HashMap<>();

  private Map<String, List<FieldMeta>> name2FieldMetas = new HashMap<>();

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
    List<FieldMeta> fieldMetas = new ArrayList<>(this.column2FieldMeta.values());
    Collections.sort(fieldMetas);
    return fieldMetas;
  }

  @Override
  public List<FieldMeta> getFieldMetas(String fieldName) {
    if (name2FieldMetas.isEmpty()) {
      return null;
    }
    List<FieldMeta> fieldMetas = new ArrayList<>(name2FieldMetas.get(fieldName));
    if (fieldMetas == null) {
      return Collections.emptyList();
    }
    Collections.sort(fieldMetas);
    return fieldMetas;
  }

  @Override
  public FieldMeta getUniqueFieldMeta(String fieldName) {
    List<FieldMeta> fieldMetas = getFieldMetas(fieldName);
    if (CollectionUtils.isEmpty(fieldMetas)) {
      return null;
    }
    if (fieldMetas.size() == 1) {
      return fieldMetas.get(0);
    }
    throw new IllegalStateException("This sheetMeta has duplicated FieldMeta for name [" + fieldName + "]");
  }

  @Override
  public FieldMeta getFieldMetas(int columnIndex) {
    if (column2FieldMeta.isEmpty()) {
      return null;
    }
    return column2FieldMeta.get(columnIndex);
  }

  @Override
  public void removeFieldMeta(String fieldName) {
    if (name2FieldMetas.isEmpty()) {
      return;
    }

    List<FieldMeta> fieldMetas = getFieldMetas(fieldName);
    if (CollectionUtils.isEmpty(fieldMetas)) {
      return;
    }

    for (FieldMeta fieldMeta : fieldMetas) {
      name2FieldMetas.get(fieldName).remove(fieldMeta);
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


    if (column2FieldMeta.containsKey(columnIndex)) {
      throw new IllegalArgumentException("This sheetMeta already has FieldMeta for column [" + columnIndex + "]");
    }

    //
    ((FieldMetaBean) fieldMeta).setSheetMeta(this);
    List<FieldMeta> internalFieldMetas = name2FieldMetas.get(fieldName);
    if (internalFieldMetas == null) {
      internalFieldMetas = new ArrayList<>();
      name2FieldMetas.put(fieldName, internalFieldMetas);
    }
    internalFieldMetas.add(fieldMeta);

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
