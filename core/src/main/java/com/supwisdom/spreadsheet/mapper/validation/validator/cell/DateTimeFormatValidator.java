package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

/**
 * 日期时间格式校验
 * Created by qianjia on 2017/2/14.
 */
public class DateTimeFormatValidator extends CustomCellValidator<DateTimeFormatValidator> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeFormatValidator.class);

  private String pattern;

  public DateTimeFormatValidator pattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

  @Override
  protected boolean doValidate(Cell cell, FieldMeta fieldMeta) {

    String value = cell.getValue();
    try {
      DateUtils.parseDateStrictly(value, pattern);
    } catch (ParseException e) {
      LOGGER.debug("Invalid date time format", e);
      return false;
    }

    return true;

  }

}
