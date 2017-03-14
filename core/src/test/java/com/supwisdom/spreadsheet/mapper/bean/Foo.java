package com.supwisdom.spreadsheet.mapper.bean;

import java.math.BigDecimal;

/**
 * Created by qianjia on 2017/3/13.
 */
public class Foo {

  private int intProperty;

  private String onlySetterProperty = "onlySetterProperty";

  private String onlyGetterProperty = "onlyGetterProperty";

  private Bar bar;

  private int int1;

  private Integer int2;

  private long long1;

  private Long long2;

  private boolean boolean1;

  private Boolean boolean2;

  private double double1;

  private Double double2;

  private float float1;

  private Float float2;

  private String string;

  private BigDecimal bigDecimal;

  private Loo loo;

  public int getIntProperty() {
    return intProperty;
  }

  public void setIntProperty(int intProperty) {
    this.intProperty = intProperty;
  }

  public Bar getBar() {
    return bar;
  }

  public void setBar(Bar bar) {
    this.bar = bar;
  }

  public String getOnlyGetterProperty() {
    return onlyGetterProperty;
  }

  public void setInt1(int int1) {
    this.int1 = int1;
  }

  public void setInt2(Integer int2) {
    this.int2 = int2;
  }

  public void setLong1(long long1) {
    this.long1 = long1;
  }

  public void setLong2(Long long2) {
    this.long2 = long2;
  }

  public void setBoolean1(boolean boolean1) {
    this.boolean1 = boolean1;
  }

  public void setBoolean2(Boolean boolean2) {
    this.boolean2 = boolean2;
  }

  public void setDouble1(double double1) {
    this.double1 = double1;
  }

  public void setDouble2(Double double2) {
    this.double2 = double2;
  }

  public void setFloat1(float float1) {
    this.float1 = float1;
  }

  public void setFloat2(Float float2) {
    this.float2 = float2;
  }

  public void setString(String string) {
    this.string = string;
  }

  public void setBigDecimal(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  public int getInt1() {
    return int1;
  }

  public Integer getInt2() {
    return int2;
  }

  public long getLong1() {
    return long1;
  }

  public Long getLong2() {
    return long2;
  }

  public boolean isBoolean1() {
    return boolean1;
  }

  public Boolean getBoolean2() {
    return boolean2;
  }

  public double getDouble1() {
    return double1;
  }

  public Double getDouble2() {
    return double2;
  }

  public float getFloat1() {
    return float1;
  }

  public Float getFloat2() {
    return float2;
  }

  public String getString() {
    return string;
  }

  public BigDecimal getBigDecimal() {
    return bigDecimal;
  }

  public void setOnlySetterProperty(String onlySetterProperty) {
    this.onlySetterProperty = onlySetterProperty;
  }

  public void setLoo(Loo loo) {
    this.loo = loo;
  }

}
