package com.supwisdom.spreadsheet.mapper.model.meta;

import java.io.Serializable;
import java.util.List;

/**
 * Sheet元信息，包含以下信息：
 * <ol>
 * <li>自己的名字</li>
 * <li>自己在Workbook中是第几个Sheet</li>
 * <li>真实数据是从第几行开始的</li>
 * <li>{@link FieldMeta}</li>
 * </ol>
 * Created by hanwen on 2016/12/27.
 */
public interface SheetMeta extends Serializable {

  /**
   * @return 自己是第几个sheet，1-based
   */
  int getSheetIndex();

  /**
   * @return Sheet名字
   */
  String getSheetName();

  /**
   * @return 数据是从第几行开始的，因为前几行可能是一些表头信息，1-based
   */
  int getDataStartRowIndex();

  /**
   * @return {@link FieldMeta}s
   */
  List<FieldMeta> getFieldMetas();

  /**
   * 根据{@link FieldMeta#getName()}来获得{@link FieldMeta}
   *
   * @param fieldName field name
   * @return {@link FieldMeta}。如果存在相同name的FieldMeta，则可能返回多个。
   */
  List<FieldMeta> getFieldMetas(String fieldName);

  /**
   * 根据{@link FieldMeta#getName()}来获得唯一的一个{@link FieldMeta}。
   * 如果存在多个name相同的FieldMeta，则会抛出异常。
   * @param fieldName field name
   * @return  {@link FieldMeta}
   */
  FieldMeta getUniqueFieldMeta(String fieldName);

  /**
   * 根据column index (1-based) 获得{@link FieldMeta}
   *
   * @param columnIndex
   * @return {@link FieldMeta}
   */
  FieldMeta getFieldMeta(int columnIndex);

  /**
   * 根据{@link FieldMeta#getName()}来获移除{@link FieldMeta}
   *
   * @param fieldName field name
   */
  void removeFieldMeta(String fieldName);

  /**
   * 添加{@link FieldMeta}
   *
   * @param fieldMeta field meta
   */
  void addFieldMeta(FieldMeta fieldMeta);

  /**
   * @return 自己所属的 {@link WorkbookMeta}
   */
  WorkbookMeta getWorkbookMeta();
}
