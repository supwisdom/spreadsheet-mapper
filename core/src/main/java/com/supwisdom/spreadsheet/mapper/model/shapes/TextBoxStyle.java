package com.supwisdom.spreadsheet.mapper.model.shapes;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;

import java.io.Serializable;

/**
 * the style of text box
 * <p>
 * Created by hanwen on 2016/12/27.
 */
public class TextBoxStyle implements Serializable {

  /**
   * 1 * 3
   */
  public static final TextBoxStyle DEFAULT = new TextBoxStyle(1, 1, 4, 4, 251, 252, 155);

  private int col1;

  private int row1;

  private int col2;

  private int row2;

  private int red;

  private int green;

  private int blue;

  public TextBoxStyle(int col1, int row1, int col2, int row2, int red, int green, int blue) {
    this.col1 = col1;
    this.row1 = row1;
    this.col2 = col2;
    this.row2 = row2;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * @return 1-based
   * @see XSSFClientAnchor#getCol1()
   */
  public int getCol1() {
    return col1;
  }

  /**
   * @return 1-based
   * @see XSSFClientAnchor#getRow1()
   */
  public int getRow1() {
    return row1;
  }

  /**
   * @return 1-based
   * @see XSSFClientAnchor#getCol2()
   */
  public int getCol2() {
    return col2;
  }

  /**
   * @return 1-based
   * @see XSSFClientAnchor#getRow2()
   */
  public int getRow2() {
    return row2;
  }

  /**
   * the text box background color
   *
   * @return rgb color red
   */
  public int getRed() {
    return red;
  }

  /**
   * the text box background color
   *
   * @return rgb color green
   */
  public int getGreen() {
    return green;
  }

  /**
   * the text box background color
   *
   * @return rgb color blue
   */
  public int getBlue() {
    return blue;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("col1", col1)
        .append("row1", row1)
        .append("col2", col2)
        .append("row2", row2)
        .append("red", red)
        .append("green", green)
        .append("blue", blue)
        .toString();
  }
}
