package com.supwisdom.spreadsheet.mapper.o2w.compose.converter;

import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * field value convert to human readable value
 * <p>
 * Created by hanwen on 4/26/16.
 */
public interface FieldConverter<T> extends Converter<T> {

  /**
   * @return which field this converter matched
   * @see FieldMeta#getName()
   */
  String getMatchField();
}
