package com.supwisdom.spreadsheet.mapper.model.meta;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

/**
 * Created by hanwen on 2016/12/30.
 */
public class FieldMetaBean implements FieldMeta {

  private String name;

  private int columnIndex;

  private Map<Integer, HeaderMeta> headerMetas = new HashMap<>();

  private SheetMeta sheetMeta;

  public FieldMetaBean(String name, int columnIndex) {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("name can not be null");
    }
    this.name = name;
    this.columnIndex = columnIndex;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getColumnIndex() {
    return columnIndex;
  }

  @Override
  public List<HeaderMeta> getHeaderMetas() {
    List<HeaderMeta> headerMetas = new ArrayList<>(this.headerMetas.values());
    Collections.sort(headerMetas);
    return headerMetas;
  }

  @Override
  public HeaderMeta getHeaderMeta(int rowIndex) {
    // maybe some row index not has field meta
    if (headerMetas.isEmpty()) {
      return null;
    }
    return headerMetas.get(rowIndex);
  }

  @Override
  public void removeHeaderMeta(int rowIndex) {
    if (headerMetas.isEmpty()) {
      return;
    }
    headerMetas.remove(rowIndex);
  }

  @Override
  public void addHeaderMeta(HeaderMeta headerMeta) {
    if (headerMeta == null) {
      throw new IllegalArgumentException("header meta can not be null");
    }
    int rowIndex = headerMeta.getRowIndex();
    if (headerMetas.containsKey(rowIndex)) {
      throw new IllegalArgumentException("the field contains multi headers at row[" + rowIndex + "]");
    }

    ((HeaderMetaBean) headerMeta).setFieldMeta(this);
    headerMetas.put(rowIndex, headerMeta);
  }

  @Override
  public SheetMeta getSheetMeta() {
    return sheetMeta;
  }

  void setSheetMeta(SheetMeta sheetMeta) {
    this.sheetMeta = sheetMeta;
  }

  @Override
  public int compareTo(FieldMeta o) {
    return new CompareToBuilder().append(columnIndex, o.getColumnIndex()).toComparison();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("name", name)
        .append("columnIndex", columnIndex)
        .toString();
  }
}

