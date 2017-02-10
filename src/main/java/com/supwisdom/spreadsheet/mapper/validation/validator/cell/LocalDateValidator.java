package com.supwisdom.spreadsheet.mapper.validation.validator.cell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

/**
 * local date validator
 * <p>
 * Created by hanwen on 2017/1/11.
 */
public class LocalDateValidator extends CustomSingleCellValidatorAdapter<LocalDateValidator> {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeValidator.class);

  private String pattern;

  public LocalDateValidator pattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

  @Override
  protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
    String value = cell.getValue();

    try {
      dateTimeFormatter.parseLocalDate(value);
    } catch (IllegalArgumentException e) {
      LOGGER.debug("{} format not valid", value);
      return false;
    }
    return true;
  }
}
