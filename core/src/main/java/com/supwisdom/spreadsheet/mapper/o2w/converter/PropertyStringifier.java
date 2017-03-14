package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * 将要转换成{@link Row}的某个Object的property转换成String的转换器
 *
 * @param <T> 要转换成{@link Row}的Object的类型
 */
public interface PropertyStringifier<T> {

  /**
   * @return which field this converter matched
   * @see FieldMeta#getName()
   */
  String getMatchField();

  /**
   * 获得某个property的字符串
   *
   * @param object    被提取property的字符串的Object
   * @param fieldMeta 相关的{@link FieldMeta}
   * @return property的字符串结果
   */
  String getPropertyString(T object, FieldMeta fieldMeta);

}
