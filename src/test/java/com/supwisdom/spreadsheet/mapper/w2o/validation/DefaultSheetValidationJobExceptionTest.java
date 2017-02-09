package com.supwisdom.spreadsheet.mapper.w2o.validation;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.CustomMultiCellValidatorAdapter;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.CustomSingleCellValidatorAdapter;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.MultiCellValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.SingleCellValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "defaultSheetValidationHelperExceptionTest")
public class DefaultSheetValidationJobExceptionTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultSheetValidationJobExceptionTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test sheet validation helper exception-------------------");
  }

  @Test(groups = "missingTest")
  public void testMissing() {

    TestCellValidator cellValidators1 = new TestCellValidator();
    cellValidators1.matchField("1");
    cellValidators1.group("1");
    cellValidators1.dependsOn("3");
    TestCellValidator cellValidators2 = new TestCellValidator();
    cellValidators2.matchField("2");
    cellValidators2.group("2");

    SheetMetaBean sheetMetaBean = new SheetMetaBean(2);
    SheetBean sheetBean = new SheetBean();
    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();
    sheetValidationJob.addDependencyValidator(cellValidators1);
    sheetValidationJob.addDependencyValidator(cellValidators2);

    boolean missing = false;
    try {
      sheetValidationJob.valid(sheetBean, sheetMetaBean);
    } catch (WorkbookValidateException e) {
      LOGGER.debug(e.getMessage());
      if (StringUtils.contains(e.getMessage(), "missing")) {
        missing = true;
      }
    }

    assertTrue(missing);
  }

  @Test(groups = "cyclingTest")
  public void testCycling0() {

    /*
        cycle
        3 -> 4
        4 -> 4
     */
    List<SingleCellValidator> singleCellValidators = new ArrayList<>();

    TestCellValidator testCellValidator1 = new TestCellValidator();
    testCellValidator1.group("3");
    testCellValidator1.matchField("1");
    testCellValidator1.dependsOn("4");
    testCellValidator1.errorMessage("test1");
    Collections.addAll(singleCellValidators, testCellValidator1);
    TestCellValidator testCellValidator2 = new TestCellValidator();
    testCellValidator2.group("4");
    testCellValidator2.matchField("2");
    testCellValidator2.errorMessage("test2");
    Collections.addAll(singleCellValidators, testCellValidator2);

    assertEquals(singleCellValidators.get(0).getMatchField(), "1");
    assertEquals(singleCellValidators.get(1).getMatchField(), "2");
    assertEquals(singleCellValidators.get(0).getGroup(), "3");
    assertEquals(singleCellValidators.get(1).getGroup(), "4");
    assertEquals(singleCellValidators.get(0).getErrorMessage(), "test1");
    assertEquals(singleCellValidators.get(1).getErrorMessage(), "test2");

    List<MultiCellValidator> rowValidators = new ArrayList<>();
    TestMultiValidator testMultiValidator = new TestMultiValidator();
    testMultiValidator.group("4");
    testMultiValidator.dependsOn("4");
    testMultiValidator.errorMessage("test2");
    rowValidators.add(testMultiValidator);

    assertValid(singleCellValidators, rowValidators, true);
  }

  @Test(groups = "cyclingTest")
  public void testCycling1() {

    /*
       no cycle
       1 -> 2, 3, 7
       2 -> 4
       3 -> 6
       4 -> 5, 6
       5 -> 7
       6 -> 7
     */
    List<SingleCellValidator> singleCellValidators = new ArrayList<>();

    TestCellValidator testCellValidator5 = new TestCellValidator();
    testCellValidator5.matchField("2");
    testCellValidator5.group("2");
    testCellValidator5.dependsOn("4");
    Collections.addAll(singleCellValidators, testCellValidator5);
    TestCellValidator testCellValidator6 = new TestCellValidator();
    testCellValidator6.matchField("3");
    testCellValidator6.group("3");
    testCellValidator6.dependsOn("6");
    Collections.addAll(singleCellValidators, testCellValidator6);
    TestCellValidator testCellValidator7 = new TestCellValidator();
    testCellValidator7.matchField("3");
    testCellValidator7.group("3");
    Collections.addAll(singleCellValidators, testCellValidator7);
    TestCellValidator testCellValidator8 = new TestCellValidator();
    testCellValidator8.matchField("3");
    testCellValidator8.group("3");
    Collections.addAll(singleCellValidators, testCellValidator8);
    TestCellValidator testCellValidator1 = new TestCellValidator();
    testCellValidator1.matchField("1");
    testCellValidator1.group("1");
    testCellValidator1.dependsOn("2");
    Collections.addAll(singleCellValidators, testCellValidator1);
    TestCellValidator testCellValidator2 = new TestCellValidator();
    testCellValidator2.matchField("1");
    testCellValidator2.group("1");
    testCellValidator2.dependsOn("3");
    Collections.addAll(singleCellValidators, testCellValidator2);
    TestCellValidator testCellValidator3 = new TestCellValidator();
    testCellValidator3.matchField("1");
    testCellValidator3.group("1");
    Collections.addAll(singleCellValidators, testCellValidator3);
    TestCellValidator testCellValidator4 = new TestCellValidator();
    testCellValidator4.matchField("1");
    testCellValidator4.group("1");
    Collections.addAll(singleCellValidators, testCellValidator4);

    TestCellValidator testCellValidator9 = new TestCellValidator();
    testCellValidator9.matchField("3");
    testCellValidator9.group("3");
    Collections.addAll(singleCellValidators, testCellValidator9);
    TestCellValidator testCellValidator10 = new TestCellValidator();
    testCellValidator10.matchField("4");
    testCellValidator10.group("4");
    testCellValidator10.dependsOn("5", "6");
    Collections.addAll(singleCellValidators, testCellValidator10);
    TestCellValidator testCellValidator11 = new TestCellValidator();
    testCellValidator11.matchField("5");
    testCellValidator11.group("5");
    testCellValidator11.dependsOn("7");
    Collections.addAll(singleCellValidators, testCellValidator11);
    TestCellValidator testCellValidator12 = new TestCellValidator();
    testCellValidator12.matchField("6");
    testCellValidator12.group("6");
    Collections.addAll(singleCellValidators, testCellValidator12);
    TestCellValidator testCellValidator13 = new TestCellValidator();
    testCellValidator13.matchField("6");
    testCellValidator13.group("6");
    testCellValidator13.dependsOn("7");
    Collections.addAll(singleCellValidators, testCellValidator13);
    TestCellValidator testCellValidator14 = new TestCellValidator();
    testCellValidator14.matchField("6");
    testCellValidator14.group("6");
    Collections.addAll(singleCellValidators, testCellValidator14);
    TestCellValidator testCellValidator15 = new TestCellValidator();
    testCellValidator15.matchField("7");
    testCellValidator15.group("7");
    Collections.addAll(singleCellValidators, testCellValidator15);

    List<MultiCellValidator> rowValidators = new ArrayList<>();
    TestMultiValidator testMultiValidator = new TestMultiValidator();
    testMultiValidator.group("1");
    testMultiValidator.matchFields("1", "2", "3", "4");
    testMultiValidator.dependsOn("7");
    rowValidators.add(testMultiValidator);

    assertValid(singleCellValidators, rowValidators, false);
  }

  @Test(groups = "cyclingTest")
  public void testCycling2() {

    /*
       cycle
       1 -> 2
       2 -> 4
       3 -> 4,5
       4 -> 6, 1
       5 -> 6
     */
    List<SingleCellValidator> singleCellValidators = new ArrayList<>();

    TestCellValidator testCellValidator1 = new TestCellValidator();
    testCellValidator1.group("1");
    testCellValidator1.matchField("1");
    testCellValidator1.dependsOn("2");
    Collections.addAll(singleCellValidators, testCellValidator1);

    TestCellValidator testCellValidator4 = new TestCellValidator();
    testCellValidator4.group("5");
    testCellValidator4.matchField("5");
    testCellValidator4.dependsOn("6");
    Collections.addAll(singleCellValidators, testCellValidator4);
    TestCellValidator testCellValidator5 = new TestCellValidator();
    testCellValidator5.group("6");
    testCellValidator5.matchField("6");
    Collections.addAll(singleCellValidators, testCellValidator5);
    TestCellValidator testCellValidator2 = new TestCellValidator();
    testCellValidator2.group("2");
    testCellValidator2.matchField("2");
    testCellValidator2.dependsOn("4");
    Collections.addAll(singleCellValidators, testCellValidator2);
    TestCellValidator testCellValidator3 = new TestCellValidator();
    testCellValidator3.group("3");
    testCellValidator3.matchField("3");
    testCellValidator3.dependsOn("4", "5");
    Collections.addAll(singleCellValidators, testCellValidator3);

    List<MultiCellValidator> rowValidators = new ArrayList<>();
    TestMultiValidator testMultiValidator = new TestMultiValidator();
    testMultiValidator.group("4");
    testMultiValidator.matchFields("4");
    testMultiValidator.dependsOn("6", "1");
    rowValidators.add(testMultiValidator);

    assertValid(singleCellValidators, rowValidators, true);
  }

  @Test(groups = "cyclingTest")
  public void testCycling3() {

    /*
      no cycle
      1 -> 2
      2 -> 3,7
      3 -> 4,5
      4 -> 8
      5 -> 6
      6 -> 8
      7 -> 5,9,11
      8 -> 9
      9 -> 10,11
      10 -> 12
      11 -> 10
     */
    List<SingleCellValidator> singleCellValidators = new ArrayList<>();
    TestCellValidator testCellValidator1 = new TestCellValidator();
    testCellValidator1.group("1");
    testCellValidator1.matchField("1");
    testCellValidator1.dependsOn("2");
    Collections.addAll(singleCellValidators, testCellValidator1);
    TestCellValidator testCellValidator2 = new TestCellValidator();
    testCellValidator2.group("2");
    testCellValidator2.matchField("2");
    testCellValidator2.dependsOn("3", "7");
    Collections.addAll(singleCellValidators, testCellValidator2);
    TestCellValidator testCellValidator3 = new TestCellValidator();
    testCellValidator3.group("3");
    testCellValidator3.matchField("3");
    testCellValidator3.dependsOn("4", "5");
    Collections.addAll(singleCellValidators, testCellValidator3);
    TestCellValidator testCellValidator4 = new TestCellValidator();
    testCellValidator4.group("4");
    testCellValidator4.matchField("4");
    testCellValidator4.dependsOn("8");
    Collections.addAll(singleCellValidators, testCellValidator4);

    TestCellValidator testCellValidator8 = new TestCellValidator();
    testCellValidator8.group("8");
    testCellValidator8.matchField("8");
    testCellValidator8.dependsOn("9");
    Collections.addAll(singleCellValidators, testCellValidator8);
    TestCellValidator testCellValidator9 = new TestCellValidator();
    testCellValidator9.group("9");
    testCellValidator9.matchField("9");
    testCellValidator9.dependsOn("10", "11");
    Collections.addAll(singleCellValidators, testCellValidator9);
    TestCellValidator testCellValidator10 = new TestCellValidator();
    testCellValidator10.group("10");
    testCellValidator10.matchField("10");
    testCellValidator10.dependsOn("12");
    Collections.addAll(singleCellValidators, testCellValidator10);
    TestCellValidator testCellValidator11 = new TestCellValidator();
    testCellValidator11.group("11");
    testCellValidator11.matchField("11");
    testCellValidator11.dependsOn("10");
    Collections.addAll(singleCellValidators, testCellValidator11);
    TestCellValidator testCellValidator12 = new TestCellValidator();
    testCellValidator12.group("12");
    testCellValidator12.matchField("12");
    Collections.addAll(singleCellValidators, testCellValidator12);
    TestCellValidator testCellValidator5 = new TestCellValidator();
    testCellValidator5.group("5");
    testCellValidator5.matchField("5");
    testCellValidator5.dependsOn("6");
    Collections.addAll(singleCellValidators, testCellValidator5);
    TestCellValidator testCellValidator6 = new TestCellValidator();
    testCellValidator6.group("6");
    testCellValidator6.matchField("6");
    testCellValidator6.dependsOn("8");
    Collections.addAll(singleCellValidators, testCellValidator6);
    TestCellValidator testCellValidator7 = new TestCellValidator();
    testCellValidator7.group("7");
    testCellValidator7.matchField("7");
    testCellValidator7.dependsOn("5", "9", "11");
    Collections.addAll(singleCellValidators, testCellValidator7);

    assertValid(singleCellValidators, Collections.<MultiCellValidator>emptyList(), false);
  }

  @Test(groups = "cyclingTest")
  public void testCycling4() {

    /*
      cycle
      1 -> 2
      2 -> 3,7
      3 -> 4,5
      4 -> 8
      5 -> 6
      6 -> 8
      7 -> 5,9,11
      8 -> 9
      9 -> 10,11
      10 -> 12
      11 -> 10,6
     */
    List<SingleCellValidator> singleCellValidators = new ArrayList<>();

    TestCellValidator testCellValidator11 = new TestCellValidator();
    testCellValidator11.group("11");
    testCellValidator11.matchField("11");
    testCellValidator11.dependsOn("10", "6");
    Collections.addAll(singleCellValidators, testCellValidator11);

    TestCellValidator testCellValidator1 = new TestCellValidator();
    testCellValidator1.group("1");
    testCellValidator1.matchField("1");
    testCellValidator1.dependsOn("2");
    Collections.addAll(singleCellValidators, testCellValidator1);
    TestCellValidator testCellValidator2 = new TestCellValidator();
    testCellValidator2.group("2");
    testCellValidator2.matchField("2");
    testCellValidator2.dependsOn("3", "7");
    Collections.addAll(singleCellValidators, testCellValidator2);
    TestCellValidator testCellValidator3 = new TestCellValidator();
    testCellValidator3.group("3");
    testCellValidator3.matchField("3");
    testCellValidator3.dependsOn("4", "5");
    Collections.addAll(singleCellValidators, testCellValidator3);
    TestCellValidator testCellValidator4 = new TestCellValidator();
    testCellValidator4.group("4");
    testCellValidator4.matchField("4");
    testCellValidator4.dependsOn("8");
    Collections.addAll(singleCellValidators, testCellValidator4);
    TestCellValidator testCellValidator5 = new TestCellValidator();
    testCellValidator5.group("5");
    testCellValidator5.matchField("5");
    testCellValidator5.dependsOn("6");
    Collections.addAll(singleCellValidators, testCellValidator5);
    TestCellValidator testCellValidator6 = new TestCellValidator();
    testCellValidator6.group("6");
    testCellValidator6.matchField("6");
    testCellValidator6.dependsOn("8");
    Collections.addAll(singleCellValidators, testCellValidator6);
    TestCellValidator testCellValidator7 = new TestCellValidator();
    testCellValidator7.group("7");
    testCellValidator7.matchField("7");
    testCellValidator7.dependsOn("5", "9", "11");
    Collections.addAll(singleCellValidators, testCellValidator7);
    TestCellValidator testCellValidator8 = new TestCellValidator();
    testCellValidator8.group("8");
    testCellValidator8.matchField("8");
    testCellValidator8.dependsOn("9");
    Collections.addAll(singleCellValidators, testCellValidator8);
    TestCellValidator testCellValidator9 = new TestCellValidator();
    testCellValidator9.group("9");
    testCellValidator9.matchField("9");
    testCellValidator9.dependsOn("10", "11");
    Collections.addAll(singleCellValidators, testCellValidator9);
    TestCellValidator testCellValidator10 = new TestCellValidator();
    testCellValidator10.group("10");
    testCellValidator10.matchField("10");
    testCellValidator10.dependsOn("12");
    Collections.addAll(singleCellValidators, testCellValidator10);
    TestCellValidator testCellValidator12 = new TestCellValidator();
    testCellValidator12.group("12");
    testCellValidator12.matchField("12");
    Collections.addAll(singleCellValidators, testCellValidator12);

    assertValid(singleCellValidators, Collections.<MultiCellValidator>emptyList(), true);
  }

  private void assertValid(List<SingleCellValidator> singleCellValidators, List<MultiCellValidator> rowValidators, boolean cycling) {
    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();

    for (MultiCellValidator rowValidator : rowValidators) {
      sheetValidationJob.addDependencyValidator(rowValidator);
    }

    for (SingleCellValidator singleCellValidator : singleCellValidators) {
      sheetValidationJob.addDependencyValidator(singleCellValidator);
    }

    boolean result = false;
    try {
      sheetValidationJob.valid(new SheetBean(), new SheetMetaBean(2));
    } catch (WorkbookValidateException e) {
      LOGGER.debug(e.getMessage());
      if (StringUtils.contains(e.getMessage(), "cycle")) {
        result = true;
      }
    }
    if (cycling) {
      assertTrue(result);
    } else {
      assertFalse(result);
    }
  }

  static class TrueCellValidator extends CustomSingleCellValidatorAdapter<TrueCellValidator> {

    private List<String> hitValidators;

    private String group;

    TrueCellValidator(List<String> hitValidators) {
      this.hitValidators = hitValidators;
    }

    @Override
    public TrueCellValidator group(String group) {
      this.group = group;
      return super.group(group);
    }

    @Override
    protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
      hitValidators.add("cell:true:" + group);
      return true;
    }

    @Override
    protected TrueCellValidator getThis() {
      return this;
    }

  }

  static class TrueMCellValidator extends CustomMultiCellValidatorAdapter<TrueMCellValidator> {

    private List<String> hitValidators;

    private String group;

    TrueMCellValidator(List<String> hitValidators) {
      this.hitValidators = hitValidators;
    }

    @Override
    public TrueMCellValidator group(String group) {
      this.group = group;
      return super.group(group);
    }

    @Override
    protected boolean customValid(List<Cell> cells, List<FieldMeta> fieldMetas) {
      hitValidators.add("row:true:" + group);
      return false;
    }

    @Override
    protected TrueMCellValidator getThis() {
      return this;
    }

  }

  static class FalseCellValidator extends CustomSingleCellValidatorAdapter<FalseCellValidator> {

    private List<String> hitValidators;

    private String group;

    FalseCellValidator(List<String> hitValidators) {
      this.hitValidators = hitValidators;
    }

    @Override
    public FalseCellValidator group(String group) {
      this.group = group;
      return super.group(group);
    }

    @Override
    protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
      hitValidators.add("cell:false:" + group);
      return false;
    }

    @Override
    protected FalseCellValidator getThis() {
      return this;
    }
  }

  static class FalseMCellValidator extends CustomMultiCellValidatorAdapter<FalseMCellValidator> {

    private List<String> hitValidators;

    private String group;

    FalseMCellValidator(List<String> hitValidators) {
      this.hitValidators = hitValidators;
    }

    @Override
    public FalseMCellValidator group(String group) {
      this.group = group;
      return super.group(group);
    }

    @Override
    protected boolean customValid(List<Cell> cells, List<FieldMeta> fieldMetas) {
      hitValidators.add("row:false:" + group);
      return false;
    }

    @Override
    protected FalseMCellValidator getThis() {
      return this;
    }
  }

  static class TestCellValidator extends CustomSingleCellValidatorAdapter<TestCellValidator> {

    private Counter counter;

    TestCellValidator() {
    }

    TestCellValidator(Counter counter) {
      this.counter = counter;
    }

    @Override
    protected boolean customValid(Cell cell, FieldMeta fieldMeta) {
      if (counter != null) {
        counter.hit();
      }
      return true;
    }

    @Override
    protected TestCellValidator getThis() {
      return this;
    }
  }

  static class TestMultiValidator extends CustomMultiCellValidatorAdapter<TestMultiValidator> {

    private Counter counter;

    TestMultiValidator(Counter counter) {
      this.counter = counter;
    }

    TestMultiValidator() {
    }

    @Override
    protected boolean customValid(List<Cell> cells, List<FieldMeta> fieldMetas) {
      if (counter != null) {
        counter.hit();
      }
      return true;
    }

    @Override
    protected TestMultiValidator getThis() {
      return this;
    }

  }

  static class Counter {
    private int count = 0;

    void hit() {
      count++;
    }

    int hitTime() {
      return count;
    }
  }

  static Sheet getSheet() {
    Sheet baseSheet = TestFactory.createSheet();
    Sheet sheet = new SheetBean();
    sheet.addRow(baseSheet.getRow(1));
    sheet.addRow(baseSheet.getRow(2));
    return sheet;
  }
}
