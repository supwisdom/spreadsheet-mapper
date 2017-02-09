package com.supwisdom.spreadsheet.mapper.o2w.compose.converter.buildin;


import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.o2w.compose.WorkbookComposeException;
import com.supwisdom.spreadsheet.mapper.o2w.compose.converter.FieldConverterAdapter;

/**
 * boolean readable text value converter
 * <p>
 * Created by hanwen on 16/3/18.
 */
public class BooleanConverter<T> extends FieldConverterAdapter<T, BooleanConverter<T>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BooleanConverter.class);

  private String trueString;

  private String falseString;

  public BooleanConverter<T> trueString(String trueString) {
    this.trueString = trueString;
    return getThis();
  }

  public BooleanConverter<T> falseString(String falseString) {
    this.falseString = falseString;
    return getThis();
  }

  @Override
  protected BooleanConverter<T> getThis() {
    return this;
  }

  @Override
  public String getValue(T object, Cell cell, FieldMeta fieldMeta) {

    try {
      Object value = PropertyUtils.getProperty(object, fieldMeta.getName());

      if (Boolean.FALSE.equals(value)) {
        return falseString;
      } else if (Boolean.TRUE.equals(value)) {
        return trueString;
      }
      return null;
    } catch (NestedNullException e) {
      LOGGER.debug("{} is null", fieldMeta.getName());
      return null;
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookComposeException(e);
    }
  }
}
