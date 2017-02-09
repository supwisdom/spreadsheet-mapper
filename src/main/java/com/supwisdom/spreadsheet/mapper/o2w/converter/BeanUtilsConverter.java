package com.supwisdom.spreadsheet.mapper.o2w.converter;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.o2w.WorkbookComposeException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.utils.FieldUtils;


/**
 * using {@link BeanUtils#getProperty(Object, String)}, this used as default converter
 * <p>
 * Created by hanwen on 15-12-16.
 */
public class BeanUtilsConverter<T> implements Converter<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtilsConverter.class);

  @Override
  public String getValue(T object, Cell cell, FieldMeta fieldMeta) {
    try {
      if (!lookup(object, fieldMeta)) {
        return null;
      }

      return BeanUtils.getProperty(object, fieldMeta.getName());
    } catch (NestedNullException e) {
      LOGGER.debug("{} is null", fieldMeta.getName());
      return null;
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookComposeException(e);
    }
  }

  private boolean lookup(T object, FieldMeta fieldMeta) {
    Class fieldType = FieldUtils.getFieldType(object.getClass(), fieldMeta.getName().split("\\."));
    return fieldType != null;
  }
}
