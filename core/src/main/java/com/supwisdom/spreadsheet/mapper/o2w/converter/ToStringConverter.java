package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 将要转换成{@link Row}的某个属性转换成String的转换器
 *
 * @param <T> 要转换成{@link Row}的对象的类型
 */
public interface ToStringConverter<T> {

  /**
   * @return which field this converter matched
   * @see FieldMeta#getName()
   */
  String getMatchField();

  /**
   * 获得字符串
   *
   * @param object
   * @param fieldMeta
   * @return
   */
  String getString(T object, FieldMeta fieldMeta);

}
