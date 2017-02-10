package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;

import java.util.*;

import static java.util.Arrays.asList;
import static org.testng.Assert.*;
import static com.supwisdom.spreadsheet.mapper.validation.DefaultSheetValidationJobExceptionTest.getSheet;

/**
 * Created by hanwen on 2017/1/22.
 */
@Test(groups = "defaultSheetValidationHelperHitTest", dependsOnGroups = "defaultSheetValidationHelperExceptionTest")
public class DefaultSheetValidationJobHitTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultSheetValidationJobHitTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test sheet validation hit helper-------------------");
  }

  @Test
  public void validatorHitTest() {

    DefaultSheetValidationJobExceptionTest.Counter counter = new DefaultSheetValidationJobExceptionTest.Counter();

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();

    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator1 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator1.group("int1");
    testCellValidator1.matchField("int1");
    testCellValidator1.dependsOn("int2");
    sheetValidationJob.addDependencyValidator(testCellValidator1);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator2 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator2.group("int2");
    testCellValidator2.matchField("int2");
    testCellValidator2.dependsOn("long1");
    sheetValidationJob.addDependencyValidator(testCellValidator2);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator3 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator3.group("long1");
    testCellValidator3.matchField("long1");
    testCellValidator3.dependsOn("long2");
    sheetValidationJob.addDependencyValidator(testCellValidator3);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator4 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator4.group("long2");
    testCellValidator4.matchField("long2");
    testCellValidator4.dependsOn("double2");
    sheetValidationJob.addDependencyValidator(testCellValidator4);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator5 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator5.group("float1");
    testCellValidator5.matchField("float1");
    testCellValidator5.dependsOn("float2");
    sheetValidationJob.addDependencyValidator(testCellValidator5);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator6 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator6.group("float2");
    testCellValidator6.matchField("float2");
    testCellValidator6.dependsOn("double2");
    sheetValidationJob.addDependencyValidator(testCellValidator6);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    testCellValidator7.dependsOn("float1");
    sheetValidationJob.addDependencyValidator(testCellValidator7);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator8 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    testCellValidator8.dependsOn("string");
    sheetValidationJob.addDependencyValidator(testCellValidator8);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator9 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");
    testCellValidator9.dependsOn("boolean1");
    sheetValidationJob.addDependencyValidator(testCellValidator9);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator10 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator10.group("string");
    testCellValidator10.matchField("string");
    testCellValidator10.dependsOn("boolean2");
    sheetValidationJob.addDependencyValidator(testCellValidator10);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator11 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator11.group("boolean1");
    testCellValidator11.matchField("boolean1");
    testCellValidator11.dependsOn("bigDecimal");
    sheetValidationJob.addDependencyValidator(testCellValidator11);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator12 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator12.group("boolean2");
    testCellValidator12.matchField("boolean2");
    testCellValidator12.dependsOn("boolean1");
    sheetValidationJob.addDependencyValidator(testCellValidator12);
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator13 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator13.group("bigDecimal");
    testCellValidator13.matchField("bigDecimal");
    sheetValidationJob.addDependencyValidator(testCellValidator13);


    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator1 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator1.group("int2");
    testMultiValidator1.matchFields("int2", "double1");
    testMultiValidator1.dependsOn("double1");
    sheetValidationJob.addDependencyValidator(testMultiValidator1);
    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator2 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator2.group("long1");
    testMultiValidator2.matchFields("int2", "double1");
    testMultiValidator2.dependsOn("float1");
    sheetValidationJob.addDependencyValidator(testMultiValidator2);
    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator3 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator3.group("double1");
    testMultiValidator3.matchFields("int2", "double1");
    testMultiValidator3.dependsOn("string");
    sheetValidationJob.addDependencyValidator(testMultiValidator3);
    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator4 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator4.group("double1");
    testMultiValidator4.matchFields("int2", "double1");
    testMultiValidator4.dependsOn("boolean2");
    sheetValidationJob.addDependencyValidator(testMultiValidator4);
    DefaultSheetValidationJobExceptionTest.TestMultiValidator testMultiValidator5 = new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter);
    testMultiValidator5.group("double1");
    testMultiValidator5.matchFields("int2", "double1");
    testMultiValidator5.dependsOn("boolean1");
    sheetValidationJob.addDependencyValidator(testMultiValidator5);

    boolean valid = sheetValidationJob.valid(sheet, sheetMeta);
    assertTrue(valid);
    assertEquals(counter.hitTime(), 13 + 5);
  }

  @Test(dependsOnMethods = "validatorHitTest")
  public void testSkip() {
    /*
       no cycle
       int1 -> int2, long1, double1
       int2 -> long2
       long1 -> float2
       long2 -> float1, float2
       float1 -> double1
       float2 -> double1
     */

    List<String> hitValidators = new ArrayList<>();
    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();

    DefaultSheetValidationJobExceptionTest.FalseMCellValidator falseMCellValidator3 = new DefaultSheetValidationJobExceptionTest.FalseMCellValidator(hitValidators);
    falseMCellValidator3.group("float1");
    falseMCellValidator3.matchFields("int1", "double1", "float1");
    falseMCellValidator3.dependsOn("double1");
    sheetValidationJob.addDependencyValidator(falseMCellValidator3);

    DefaultSheetValidationJobExceptionTest.TrueCellValidator trueCellValidator1 = new DefaultSheetValidationJobExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator1.group("float2");
    trueCellValidator1.matchField("float2");
    trueCellValidator1.dependsOn("double1");
    sheetValidationJob.addDependencyValidator(trueCellValidator1);
    DefaultSheetValidationJobExceptionTest.FalseCellValidator falseCellValidator1 = new DefaultSheetValidationJobExceptionTest.FalseCellValidator(hitValidators);
    falseCellValidator1.group("int1");
    falseCellValidator1.matchField("int1");
    falseCellValidator1.dependsOn("int2");
    sheetValidationJob.addDependencyValidator(falseCellValidator1);
    DefaultSheetValidationJobExceptionTest.TrueCellValidator trueCellValidator2 = new DefaultSheetValidationJobExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator2.group("int1");
    trueCellValidator2.matchField("int1");
    trueCellValidator2.dependsOn("long1");
    sheetValidationJob.addDependencyValidator(trueCellValidator2);
    DefaultSheetValidationJobExceptionTest.TrueCellValidator trueCellValidator3 = new DefaultSheetValidationJobExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator3.group("int2");
    trueCellValidator3.matchField("int2");
    trueCellValidator3.dependsOn("long2");
    sheetValidationJob.addDependencyValidator(trueCellValidator3);
    DefaultSheetValidationJobExceptionTest.TrueCellValidator trueCellValidator4 = new DefaultSheetValidationJobExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator4.group("long1");
    trueCellValidator4.matchField("long1");
    trueCellValidator4.dependsOn("float2");
    sheetValidationJob.addDependencyValidator(trueCellValidator4);
    DefaultSheetValidationJobExceptionTest.TrueCellValidator trueCellValidator5 = new DefaultSheetValidationJobExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator5.group("long2");
    trueCellValidator5.matchField("long2");
    trueCellValidator5.dependsOn("float1");
    sheetValidationJob.addDependencyValidator(trueCellValidator5);
    DefaultSheetValidationJobExceptionTest.FalseCellValidator falseCellValidator2 = new DefaultSheetValidationJobExceptionTest.FalseCellValidator(hitValidators);
    falseCellValidator2.group("float1");
    falseCellValidator2.matchField("float1");
    sheetValidationJob.addDependencyValidator(falseCellValidator2);
    DefaultSheetValidationJobExceptionTest.TrueCellValidator trueCellValidator6 = new DefaultSheetValidationJobExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator6.group("double1");
    trueCellValidator6.matchField("double1");
    sheetValidationJob.addDependencyValidator(trueCellValidator6);

    DefaultSheetValidationJobExceptionTest.TrueMCellValidator trueMCellValidator1 = new DefaultSheetValidationJobExceptionTest.TrueMCellValidator(hitValidators);
    trueMCellValidator1.group("int1");
    trueMCellValidator1.matchFields("int1", "double1", "float1");
    trueMCellValidator1.dependsOn("double1");
    sheetValidationJob.addDependencyValidator(trueMCellValidator1);
    DefaultSheetValidationJobExceptionTest.TrueMCellValidator trueMCellValidator2 = new DefaultSheetValidationJobExceptionTest.TrueMCellValidator(hitValidators);
    trueMCellValidator2.group("long2");
    trueMCellValidator2.matchFields("int1", "double1", "float1");
    trueMCellValidator2.dependsOn("float2");
    sheetValidationJob.addDependencyValidator(trueMCellValidator2);
    boolean result = sheetValidationJob.valid(sheet, sheetMeta);
    assertFalse(result);

    List<String> expected = asList("cell:true:double1", "row:false:float1", "cell:true:float2", "cell:true:long1");

    assertEquals(hitValidators, expected);
  }

  @Test(dependsOnMethods = "testSkip")
  public void testSkip2() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    DefaultSheetValidationJobExceptionTest.Counter counter = new DefaultSheetValidationJobExceptionTest.Counter();

    TrueTestRowValidator trueTestRowValidator = new TrueTestRowValidator(counter, "int1", "int2");
    TrueTestSheetValidator trueTestSheetValidator = new TrueTestSheetValidator(counter);


    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator8 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator9 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");

    SheetValidationJob sheetValidationJob1 = new DefaultSheetValidationJob();
    sheetValidationJob1.addDependencyValidator(testCellValidator7);
    sheetValidationJob1.addDependencyValidator(testCellValidator8);
    sheetValidationJob1.addDependencyValidator(testCellValidator9);


    sheetValidationJob1.addRowValidator(trueTestRowValidator);

    sheetValidationJob1.addSheetValidator(trueTestSheetValidator);

    boolean valid = sheetValidationJob1.valid(sheet, sheetMeta);

    assertTrue(valid);
    assertEquals(counter.hitTime(), 1 + 2 + 3);

  }

  @Test(dependsOnMethods = "testSkip2")
  public void testSkip3() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    DefaultSheetValidationJobExceptionTest.Counter counter = new DefaultSheetValidationJobExceptionTest.Counter();

    FalseTestRowValidator falseTestRowValidator = new FalseTestRowValidator(counter, "int1", "int2");

    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator8 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator9 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");

    SheetValidationJob sheetValidationJob1 = new DefaultSheetValidationJob();
    sheetValidationJob1.addDependencyValidator(testCellValidator7);
    sheetValidationJob1.addDependencyValidator(testCellValidator8);
    sheetValidationJob1.addDependencyValidator(testCellValidator9);


    sheetValidationJob1.addRowValidator(falseTestRowValidator);

    boolean valid = sheetValidationJob1.valid(sheet, sheetMeta);

    assertFalse(valid);
    assertEquals(counter.hitTime(), 2);

  }

  @Test(dependsOnMethods = "testSkip3")
  public void testSkip4() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    DefaultSheetValidationJobExceptionTest.Counter counter = new DefaultSheetValidationJobExceptionTest.Counter();

    FalseTestSheetValidator falseTestSheetValidator = new FalseTestSheetValidator(counter);

    DefaultSheetValidationJobExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");

    SheetValidationJob sheetValidationJob1 = new DefaultSheetValidationJob();
    sheetValidationJob1.addDependencyValidator(testCellValidator7);


    sheetValidationJob1.addSheetValidator(falseTestSheetValidator);

    boolean valid = sheetValidationJob1.valid(sheet, sheetMeta);

    assertFalse(valid);
    assertEquals(counter.hitTime(), 1);

  }

  @Test(dependsOnMethods = "testSkip4")
  public void testSkip5() {

    SheetMeta sheetMeta = new SheetMetaBean(2);
    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();
    fieldMetaMap.remove("long1");
    fieldMetaMap.remove("string");

    for (FieldMeta fieldMeta : fieldMetaMap.values()) {
      fieldMeta.addHeaderMeta(new HeaderMetaBean(1, fieldMeta.getName()));
      sheetMeta.addFieldMeta(fieldMeta);
    }

    DefaultSheetValidationJobExceptionTest.Counter counter = new DefaultSheetValidationJobExceptionTest.Counter();

    Sheet sheet = getSheet();

    DefaultSheetValidationJob defaultSheetValidationHelper = new DefaultSheetValidationJob();

    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter).group("int1").matchField("int1"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter).group("int2").matchField("int2").dependsOn("int1"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationJobExceptionTest.TestMultiValidator(counter).group("int1").matchFields("long1", "long2"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter).group("string").matchField("string"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter).group("float1").matchField("float1").dependsOn("int2"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationJobExceptionTest.TestCellValidator(counter).group("float2").matchField("float2").dependsOn("int2"));

    boolean valid = defaultSheetValidationHelper.valid(sheet, sheetMeta);

    assertTrue(valid);
    assertEquals(counter.hitTime(), 4);
  }

  private class TrueTestRowValidator implements RowValidator {

    private DefaultSheetValidationJobExceptionTest.Counter counter;

    private Set<String> messageOnFields = new HashSet<>();

    TrueTestRowValidator(DefaultSheetValidationJobExceptionTest.Counter counter, String... messageOnFields) {
      this.counter = counter;
      Collections.addAll(this.messageOnFields, messageOnFields);
    }

    @Override
    public String getErrorMessage() {
      return "row error";
    }

    @Override
    public boolean valid(Row row, SheetMeta sheetMeta) {
      counter.hit();
      return true;
    }

    @Override
    public Set<String> getMessageOnFields() {
      return messageOnFields;
    }
  }

  private class FalseTestRowValidator implements RowValidator {

    private DefaultSheetValidationJobExceptionTest.Counter counter;

    private Set<String> messageOnFields = new HashSet<>();

    FalseTestRowValidator(DefaultSheetValidationJobExceptionTest.Counter counter, String... messageOnFields) {
      this.counter = counter;
      Collections.addAll(this.messageOnFields, messageOnFields);
    }

    @Override
    public String getErrorMessage() {
      return "row error";
    }

    @Override
    public boolean valid(Row row, SheetMeta sheetMeta) {
      counter.hit();
      return false;
    }

    @Override
    public Set<String> getMessageOnFields() {
      return messageOnFields;
    }

  }

  private class TrueTestSheetValidator implements SheetValidator {

    private DefaultSheetValidationJobExceptionTest.Counter counter;

    TrueTestSheetValidator(DefaultSheetValidationJobExceptionTest.Counter counter) {
      this.counter = counter;
    }

    @Override
    public String getErrorMessage() {
      return "sheet error";
    }

    @Override
    public boolean valid(Sheet sheet, SheetMeta sheetMeta) {
      counter.hit();
      ;
      return true;
    }
  }

  private class FalseTestSheetValidator implements SheetValidator {

    private DefaultSheetValidationJobExceptionTest.Counter counter;

    FalseTestSheetValidator(DefaultSheetValidationJobExceptionTest.Counter counter) {
      this.counter = counter;
    }

    @Override
    public String getErrorMessage() {
      return "sheet error";
    }

    @Override
    public boolean valid(Sheet sheet, SheetMeta sheetMeta) {
      counter.hit();
      return false;
    }
  }
}
