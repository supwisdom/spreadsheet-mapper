package com.supwisdom.spreadsheet.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestBean {

  private Long long1;

  private LocalDate localDate;

  private LocalDateTime localDateTime;

  public Long getLong1() {
    return long1;
  }

  public void setLong1(Long long1) {
    this.long1 = long1;
  }

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
