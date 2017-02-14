package com.supwisdom.spreadsheet.mapper.validation.testvalidator;

public class Counter {
  private int count = 0;

  public void hit() {
    count++;
  }

  public int hitTime() {
    return count;
  }
}

