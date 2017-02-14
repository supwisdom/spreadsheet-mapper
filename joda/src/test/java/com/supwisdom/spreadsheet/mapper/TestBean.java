package com.supwisdom.spreadsheet.mapper;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by qianjia on 2017/2/14.
 */
public class TestBean {

  private LocalDate localDate;

  private LocalDateTime localDateTime;

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

}
