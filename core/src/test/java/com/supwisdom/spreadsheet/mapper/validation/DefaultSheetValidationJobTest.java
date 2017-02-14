package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.validation.testvalidator.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.testng.Assert.*;

/**
 * Created by hanwen on 2017/1/22.
 */
@Test(groups = "defaultSheetValidationJobTest", dependsOnGroups = "graphCyclicCheckerTest")
public class DefaultSheetValidationJobTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultSheetValidationJobTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test sheet validation hit helper-------------------");
  }

  @Test
  public void validatorHitTest() {

    Counter counter = new Counter();

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    SheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();

    TestCellValidator testCellValidator1 = new TestCellValidator(counter);
    testCellValidator1.group("int1");
    testCellValidator1.matchField("int1");
    testCellValidator1.dependsOn("int2");
    sheetValidationJob.addCellValidator(testCellValidator1);
    TestCellValidator testCellValidator2 = new TestCellValidator(counter);
    testCellValidator2.group("int2");
    testCellValidator2.matchField("int2");
    testCellValidator2.dependsOn("long1");
    sheetValidationJob.addCellValidator(testCellValidator2);
    TestCellValidator testCellValidator3 = new TestCellValidator(counter);
    testCellValidator3.group("long1");
    testCellValidator3.matchField("long1");
    testCellValidator3.dependsOn("long2");
    sheetValidationJob.addCellValidator(testCellValidator3);
    TestCellValidator testCellValidator4 = new TestCellValidator(counter);
    testCellValidator4.group("long2");
    testCellValidator4.matchField("long2");
    testCellValidator4.dependsOn("double2");
    sheetValidationJob.addCellValidator(testCellValidator4);
    TestCellValidator testCellValidator5 = new TestCellValidator(counter);
    testCellValidator5.group("float1");
    testCellValidator5.matchField("float1");
    testCellValidator5.dependsOn("float2");
    sheetValidationJob.addCellValidator(testCellValidator5);
    TestCellValidator testCellValidator6 = new TestCellValidator(counter);
    testCellValidator6.group("float2");
    testCellValidator6.matchField("float2");
    testCellValidator6.dependsOn("double2");
    sheetValidationJob.addCellValidator(testCellValidator6);
    TestCellValidator testCellValidator7 = new TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    testCellValidator7.dependsOn("float1");
    sheetValidationJob.addCellValidator(testCellValidator7);
    TestCellValidator testCellValidator8 = new TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    testCellValidator8.dependsOn("string");
    sheetValidationJob.addCellValidator(testCellValidator8);
    TestCellValidator testCellValidator9 = new TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");
    testCellValidator9.dependsOn("boolean1");
    sheetValidationJob.addCellValidator(testCellValidator9);
    TestCellValidator testCellValidator10 = new TestCellValidator(counter);
    testCellValidator10.group("string");
    testCellValidator10.matchField("string");
    testCellValidator10.dependsOn("boolean2");
    sheetValidationJob.addCellValidator(testCellValidator10);
    TestCellValidator testCellValidator11 = new TestCellValidator(counter);
    testCellValidator11.group("boolean1");
    testCellValidator11.matchField("boolean1");
    testCellValidator11.dependsOn("bigDecimal");
    sheetValidationJob.addCellValidator(testCellValidator11);
    TestCellValidator testCellValidator12 = new TestCellValidator(counter);
    testCellValidator12.group("boolean2");
    testCellValidator12.matchField("boolean2");
    testCellValidator12.dependsOn("boolean1");
    sheetValidationJob.addCellValidator(testCellValidator12);
    TestCellValidator testCellValidator13 = new TestCellValidator(counter);
    testCellValidator13.group("bigDecimal");
    testCellValidator13.matchField("bigDecimal");
    sheetValidationJob.addCellValidator(testCellValidator13);

    TestMultiValidator testMultiValidator1 = new TestMultiValidator(counter);
    testMultiValidator1.group("int2");
    testMultiValidator1.matchFields("int2", "double1");
    testMultiValidator1.dependsOn("double1");
    sheetValidationJob.addUnionCellValidator(testMultiValidator1);
    TestMultiValidator testMultiValidator2 = new TestMultiValidator(counter);
    testMultiValidator2.group("long1");
    testMultiValidator2.matchFields("int2", "double1");
    testMultiValidator2.dependsOn("float1");
    sheetValidationJob.addUnionCellValidator(testMultiValidator2);
    TestMultiValidator testMultiValidator3 = new TestMultiValidator(counter);
    testMultiValidator3.group("double1");
    testMultiValidator3.matchFields("int2", "double1");
    testMultiValidator3.dependsOn("string");
    sheetValidationJob.addUnionCellValidator(testMultiValidator3);
    TestMultiValidator testMultiValidator4 = new TestMultiValidator(counter);
    testMultiValidator4.group("double1");
    testMultiValidator4.matchFields("int2", "double1");
    testMultiValidator4.dependsOn("boolean2");
    sheetValidationJob.addUnionCellValidator(testMultiValidator4);
    TestMultiValidator testMultiValidator5 = new TestMultiValidator(counter);
    testMultiValidator5.group("double1");
    testMultiValidator5.matchFields("int2", "double1");
    testMultiValidator5.dependsOn("boolean1");
    sheetValidationJob.addUnionCellValidator(testMultiValidator5);

    boolean valid = sheetValidationJob.validate(sheet, sheetMeta);
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

    FalseMCellValidator falseMCellValidator3 = new FalseMCellValidator(hitValidators);
    falseMCellValidator3.group("float1");
    falseMCellValidator3.matchFields("int1", "double1", "float1");
    falseMCellValidator3.dependsOn("double1");
    sheetValidationJob.addUnionCellValidator(falseMCellValidator3);

    TrueCellValidator trueCellValidator1 = new TrueCellValidator(hitValidators);
    trueCellValidator1.group("float2");
    trueCellValidator1.matchField("float2");
    trueCellValidator1.dependsOn("double1");
    sheetValidationJob.addCellValidator(trueCellValidator1);
    FalseCellValidator falseCellValidator1 = new FalseCellValidator(hitValidators);
    falseCellValidator1.group("int1");
    falseCellValidator1.matchField("int1");
    falseCellValidator1.dependsOn("int2");
    sheetValidationJob.addCellValidator(falseCellValidator1);
    TrueCellValidator trueCellValidator2 = new TrueCellValidator(hitValidators);
    trueCellValidator2.group("int1");
    trueCellValidator2.matchField("int1");
    trueCellValidator2.dependsOn("long1");
    sheetValidationJob.addCellValidator(trueCellValidator2);
    TrueCellValidator trueCellValidator3 = new TrueCellValidator(hitValidators);
    trueCellValidator3.group("int2");
    trueCellValidator3.matchField("int2");
    trueCellValidator3.dependsOn("long2");
    sheetValidationJob.addCellValidator(trueCellValidator3);
    TrueCellValidator trueCellValidator4 = new TrueCellValidator(hitValidators);
    trueCellValidator4.group("long1");
    trueCellValidator4.matchField("long1");
    trueCellValidator4.dependsOn("float2");
    sheetValidationJob.addCellValidator(trueCellValidator4);
    TrueCellValidator trueCellValidator5 = new TrueCellValidator(hitValidators);
    trueCellValidator5.group("long2");
    trueCellValidator5.matchField("long2");
    trueCellValidator5.dependsOn("float1");
    sheetValidationJob.addCellValidator(trueCellValidator5);
    FalseCellValidator falseCellValidator2 = new FalseCellValidator(hitValidators);
    falseCellValidator2.group("float1");
    falseCellValidator2.matchField("float1");
    sheetValidationJob.addCellValidator(falseCellValidator2);
    TrueCellValidator trueCellValidator6 = new TrueCellValidator(hitValidators);
    trueCellValidator6.group("double1");
    trueCellValidator6.matchField("double1");
    sheetValidationJob.addCellValidator(trueCellValidator6);

    TrueMCellValidator trueMCellValidator1 = new TrueMCellValidator(hitValidators);
    trueMCellValidator1.group("int1");
    trueMCellValidator1.matchFields("int1", "double1", "float1");
    trueMCellValidator1.dependsOn("double1");
    sheetValidationJob.addUnionCellValidator(trueMCellValidator1);
    TrueMCellValidator trueMCellValidator2 = new TrueMCellValidator(hitValidators);
    trueMCellValidator2.group("long2");
    trueMCellValidator2.matchFields("int1", "double1", "float1");
    trueMCellValidator2.dependsOn("float2");
    sheetValidationJob.addUnionCellValidator(trueMCellValidator2);
    boolean result = sheetValidationJob.validate(sheet, sheetMeta);
    assertFalse(result);

    List<String> expected = asList("cell:true:double1", "row:false:float1", "cell:true:float2", "cell:true:long1");

    assertEquals(hitValidators, expected);
  }

  @Test(dependsOnMethods = "testSkip")
  public void testSkip2() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    Counter counter = new Counter();

    TrueTestRowValidator trueTestRowValidator = new TrueTestRowValidator(counter, "int1", "int2");
    TrueTestSheetValidator trueTestSheetValidator = new TrueTestSheetValidator(counter);

    TestCellValidator testCellValidator7 = new TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    TestCellValidator testCellValidator8 = new TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    TestCellValidator testCellValidator9 = new TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");

    SheetValidationJob sheetValidationJob1 = new DefaultSheetValidationJob();
    sheetValidationJob1.addCellValidator(testCellValidator7);
    sheetValidationJob1.addCellValidator(testCellValidator8);
    sheetValidationJob1.addCellValidator(testCellValidator9);

    sheetValidationJob1.addRowValidator(trueTestRowValidator);

    sheetValidationJob1.addSheetValidator(trueTestSheetValidator);

    boolean valid = sheetValidationJob1.validate(sheet, sheetMeta);

    assertTrue(valid);
    assertEquals(counter.hitTime(), 1 + 2 + 3);

  }

  @Test(dependsOnMethods = "testSkip2")
  public void testSkip3() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    Counter counter = new Counter();

    FalseTestRowValidator falseTestRowValidator = new FalseTestRowValidator(counter, "int1", "int2");

    TestCellValidator testCellValidator7 = new TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    TestCellValidator testCellValidator8 = new TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    TestCellValidator testCellValidator9 = new TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");

    SheetValidationJob sheetValidationJob1 = new DefaultSheetValidationJob();
    sheetValidationJob1.addCellValidator(testCellValidator7);
    sheetValidationJob1.addCellValidator(testCellValidator8);
    sheetValidationJob1.addCellValidator(testCellValidator9);

    sheetValidationJob1.addRowValidator(falseTestRowValidator);

    boolean valid = sheetValidationJob1.validate(sheet, sheetMeta);

    assertFalse(valid);
    assertEquals(counter.hitTime(), 2);

  }

  @Test(dependsOnMethods = "testSkip3")
  public void testSkip4() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    Counter counter = new Counter();

    FalseTestSheetValidator falseTestSheetValidator = new FalseTestSheetValidator(counter);

    TestCellValidator testCellValidator7 = new TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");

    SheetValidationJob sheetValidationJob1 = new DefaultSheetValidationJob();
    sheetValidationJob1.addCellValidator(testCellValidator7);

    sheetValidationJob1.addSheetValidator(falseTestSheetValidator);

    boolean valid = sheetValidationJob1.validate(sheet, sheetMeta);

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

    Counter counter = new Counter();

    Sheet sheet = getSheet();

    DefaultSheetValidationJob defaultSheetValidationJob = new DefaultSheetValidationJob();

    defaultSheetValidationJob.addCellValidator(
        new TestCellValidator(counter).group("int1").matchField("int1"));
    defaultSheetValidationJob.addCellValidator(
        new TestCellValidator(counter).group("int2").matchField("int2").dependsOn("int1"));
    defaultSheetValidationJob.addUnionCellValidator(
        new TestMultiValidator(counter).group("int1").matchFields("long1", "long2"));
    defaultSheetValidationJob.addCellValidator(
        new TestCellValidator(counter).group("string").matchField("string"));
    defaultSheetValidationJob.addCellValidator(
        new TestCellValidator(counter).group("float1").matchField("float1").dependsOn("int2"));
    defaultSheetValidationJob.addCellValidator(
        new TestCellValidator(counter).group("float2").matchField("float2").dependsOn("int2"));

    boolean valid = defaultSheetValidationJob.validate(sheet, sheetMeta);

    assertTrue(valid);
    assertEquals(counter.hitTime(), 4);
  }

  private Sheet getSheet() {
    Sheet baseSheet = TestFactory.createSheet();
    Sheet sheet = new SheetBean();
    sheet.addRow(baseSheet.getRow(1));
    sheet.addRow(baseSheet.getRow(2));
    return sheet;
  }

}
