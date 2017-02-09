package com.supwisdom.spreadsheet.mapper.w2o.validation;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.sheet.SheetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.HeaderMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.row.RowValidator;

import java.util.*;

import static java.util.Arrays.asList;
import static org.testng.Assert.*;
import static com.supwisdom.spreadsheet.mapper.w2o.validation.DefaultSheetValidationHelperExceptionTest.getSheet;

/**
 * Created by hanwen on 2017/1/22.
 */
@Test(groups = "defaultSheetValidationHelperHitTest", dependsOnGroups = "defaultSheetValidationHelperExceptionTest")
public class DefaultSheetValidationHelperHitTest {

  private static Logger LOGGER = LoggerFactory.getLogger(DefaultSheetValidationHelperHitTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test sheet validation hit helper-------------------");
  }

  @Test
  public void validatorHitTest() {

    DefaultSheetValidationHelperExceptionTest.Counter counter = new DefaultSheetValidationHelperExceptionTest.Counter();

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    SheetValidationHelper sheetValidationHelper = new DefaultSheetValidationHelper();

    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator1 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator1.group("int1");
    testCellValidator1.matchField("int1");
    testCellValidator1.dependsOn("int2");
    sheetValidationHelper.addDependencyValidator(testCellValidator1);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator2 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator2.group("int2");
    testCellValidator2.matchField("int2");
    testCellValidator2.dependsOn("long1");
    sheetValidationHelper.addDependencyValidator(testCellValidator2);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator3 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator3.group("long1");
    testCellValidator3.matchField("long1");
    testCellValidator3.dependsOn("long2");
    sheetValidationHelper.addDependencyValidator(testCellValidator3);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator4 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator4.group("long2");
    testCellValidator4.matchField("long2");
    testCellValidator4.dependsOn("double2");
    sheetValidationHelper.addDependencyValidator(testCellValidator4);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator5 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator5.group("float1");
    testCellValidator5.matchField("float1");
    testCellValidator5.dependsOn("float2");
    sheetValidationHelper.addDependencyValidator(testCellValidator5);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator6 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator6.group("float2");
    testCellValidator6.matchField("float2");
    testCellValidator6.dependsOn("double2");
    sheetValidationHelper.addDependencyValidator(testCellValidator6);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    testCellValidator7.dependsOn("float1");
    sheetValidationHelper.addDependencyValidator(testCellValidator7);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator8 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    testCellValidator8.dependsOn("string");
    sheetValidationHelper.addDependencyValidator(testCellValidator8);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator9 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");
    testCellValidator9.dependsOn("boolean1");
    sheetValidationHelper.addDependencyValidator(testCellValidator9);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator10 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator10.group("string");
    testCellValidator10.matchField("string");
    testCellValidator10.dependsOn("boolean2");
    sheetValidationHelper.addDependencyValidator(testCellValidator10);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator11 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator11.group("boolean1");
    testCellValidator11.matchField("boolean1");
    testCellValidator11.dependsOn("bigDecimal");
    sheetValidationHelper.addDependencyValidator(testCellValidator11);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator12 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator12.group("boolean2");
    testCellValidator12.matchField("boolean2");
    testCellValidator12.dependsOn("boolean1");
    sheetValidationHelper.addDependencyValidator(testCellValidator12);
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator13 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator13.group("bigDecimal");
    testCellValidator13.matchField("bigDecimal");
    sheetValidationHelper.addDependencyValidator(testCellValidator13);


    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator1 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator1.group("int2");
    testMultiValidator1.matchFields("int2", "double1");
    testMultiValidator1.dependsOn("double1");
    sheetValidationHelper.addDependencyValidator(testMultiValidator1);
    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator2 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator2.group("long1");
    testMultiValidator2.matchFields("int2", "double1");
    testMultiValidator2.dependsOn("float1");
    sheetValidationHelper.addDependencyValidator(testMultiValidator2);
    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator3 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator3.group("double1");
    testMultiValidator3.matchFields("int2", "double1");
    testMultiValidator3.dependsOn("string");
    sheetValidationHelper.addDependencyValidator(testMultiValidator3);
    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator4 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator4.group("double1");
    testMultiValidator4.matchFields("int2", "double1");
    testMultiValidator4.dependsOn("boolean2");
    sheetValidationHelper.addDependencyValidator(testMultiValidator4);
    DefaultSheetValidationHelperExceptionTest.TestMultiValidator testMultiValidator5 = new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter);
    testMultiValidator5.group("double1");
    testMultiValidator5.matchFields("int2", "double1");
    testMultiValidator5.dependsOn("boolean1");
    sheetValidationHelper.addDependencyValidator(testMultiValidator5);

    boolean valid = sheetValidationHelper.valid(sheet, sheetMeta);
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

    SheetValidationHelper sheetValidationHelper = new DefaultSheetValidationHelper();

    DefaultSheetValidationHelperExceptionTest.FalseMCellValidator falseMCellValidator3 = new DefaultSheetValidationHelperExceptionTest.FalseMCellValidator(hitValidators);
    falseMCellValidator3.group("float1");
    falseMCellValidator3.matchFields("int1", "double1", "float1");
    falseMCellValidator3.dependsOn("double1");
    sheetValidationHelper.addDependencyValidator(falseMCellValidator3);

    DefaultSheetValidationHelperExceptionTest.TrueCellValidator trueCellValidator1 = new DefaultSheetValidationHelperExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator1.group("float2");
    trueCellValidator1.matchField("float2");
    trueCellValidator1.dependsOn("double1");
    sheetValidationHelper.addDependencyValidator(trueCellValidator1);
    DefaultSheetValidationHelperExceptionTest.FalseCellValidator falseCellValidator1 = new DefaultSheetValidationHelperExceptionTest.FalseCellValidator(hitValidators);
    falseCellValidator1.group("int1");
    falseCellValidator1.matchField("int1");
    falseCellValidator1.dependsOn("int2");
    sheetValidationHelper.addDependencyValidator(falseCellValidator1);
    DefaultSheetValidationHelperExceptionTest.TrueCellValidator trueCellValidator2 = new DefaultSheetValidationHelperExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator2.group("int1");
    trueCellValidator2.matchField("int1");
    trueCellValidator2.dependsOn("long1");
    sheetValidationHelper.addDependencyValidator(trueCellValidator2);
    DefaultSheetValidationHelperExceptionTest.TrueCellValidator trueCellValidator3 = new DefaultSheetValidationHelperExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator3.group("int2");
    trueCellValidator3.matchField("int2");
    trueCellValidator3.dependsOn("long2");
    sheetValidationHelper.addDependencyValidator(trueCellValidator3);
    DefaultSheetValidationHelperExceptionTest.TrueCellValidator trueCellValidator4 = new DefaultSheetValidationHelperExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator4.group("long1");
    trueCellValidator4.matchField("long1");
    trueCellValidator4.dependsOn("float2");
    sheetValidationHelper.addDependencyValidator(trueCellValidator4);
    DefaultSheetValidationHelperExceptionTest.TrueCellValidator trueCellValidator5 = new DefaultSheetValidationHelperExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator5.group("long2");
    trueCellValidator5.matchField("long2");
    trueCellValidator5.dependsOn("float1");
    sheetValidationHelper.addDependencyValidator(trueCellValidator5);
    DefaultSheetValidationHelperExceptionTest.FalseCellValidator falseCellValidator2 = new DefaultSheetValidationHelperExceptionTest.FalseCellValidator(hitValidators);
    falseCellValidator2.group("float1");
    falseCellValidator2.matchField("float1");
    sheetValidationHelper.addDependencyValidator(falseCellValidator2);
    DefaultSheetValidationHelperExceptionTest.TrueCellValidator trueCellValidator6 = new DefaultSheetValidationHelperExceptionTest.TrueCellValidator(hitValidators);
    trueCellValidator6.group("double1");
    trueCellValidator6.matchField("double1");
    sheetValidationHelper.addDependencyValidator(trueCellValidator6);

    DefaultSheetValidationHelperExceptionTest.TrueMCellValidator trueMCellValidator1 = new DefaultSheetValidationHelperExceptionTest.TrueMCellValidator(hitValidators);
    trueMCellValidator1.group("int1");
    trueMCellValidator1.matchFields("int1", "double1", "float1");
    trueMCellValidator1.dependsOn("double1");
    sheetValidationHelper.addDependencyValidator(trueMCellValidator1);
    DefaultSheetValidationHelperExceptionTest.TrueMCellValidator trueMCellValidator2 = new DefaultSheetValidationHelperExceptionTest.TrueMCellValidator(hitValidators);
    trueMCellValidator2.group("long2");
    trueMCellValidator2.matchFields("int1", "double1", "float1");
    trueMCellValidator2.dependsOn("float2");
    sheetValidationHelper.addDependencyValidator(trueMCellValidator2);
    boolean result = sheetValidationHelper.valid(sheet, sheetMeta);
    assertFalse(result);

    List<String> expected = asList("cell:true:double1", "row:false:float1", "cell:true:float2", "cell:true:long1");

    assertEquals(hitValidators, expected);
  }

  @Test(dependsOnMethods = "testSkip")
  public void testSkip2() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    DefaultSheetValidationHelperExceptionTest.Counter counter = new DefaultSheetValidationHelperExceptionTest.Counter();

    TrueTestRowValidator trueTestRowValidator = new TrueTestRowValidator(counter, "int1", "int2");
    TrueTestSheetValidator trueTestSheetValidator = new TrueTestSheetValidator(counter);


    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator8 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator9 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");

    SheetValidationHelper sheetValidationHelper1 = new DefaultSheetValidationHelper();
    sheetValidationHelper1.addDependencyValidator(testCellValidator7);
    sheetValidationHelper1.addDependencyValidator(testCellValidator8);
    sheetValidationHelper1.addDependencyValidator(testCellValidator9);


    sheetValidationHelper1.addRowValidator(trueTestRowValidator);

    sheetValidationHelper1.addSheetValidator(trueTestSheetValidator);

    boolean valid = sheetValidationHelper1.valid(sheet, sheetMeta);

    assertTrue(valid);
    assertEquals(counter.hitTime(), 1 + 2 + 3);

  }

  @Test(dependsOnMethods = "testSkip2")
  public void testSkip3() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    DefaultSheetValidationHelperExceptionTest.Counter counter = new DefaultSheetValidationHelperExceptionTest.Counter();

    FalseTestRowValidator falseTestRowValidator = new FalseTestRowValidator(counter, "int1", "int2");

    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator8 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator8.group("double2");
    testCellValidator8.matchField("double2");
    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator9 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator9.group("string");
    testCellValidator9.matchField("string");

    SheetValidationHelper sheetValidationHelper1 = new DefaultSheetValidationHelper();
    sheetValidationHelper1.addDependencyValidator(testCellValidator7);
    sheetValidationHelper1.addDependencyValidator(testCellValidator8);
    sheetValidationHelper1.addDependencyValidator(testCellValidator9);


    sheetValidationHelper1.addRowValidator(falseTestRowValidator);

    boolean valid = sheetValidationHelper1.valid(sheet, sheetMeta);

    assertFalse(valid);
    assertEquals(counter.hitTime(), 2);

  }

  @Test(dependsOnMethods = "testSkip3")
  public void testSkip4() {

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);
    Sheet sheet = getSheet();

    DefaultSheetValidationHelperExceptionTest.Counter counter = new DefaultSheetValidationHelperExceptionTest.Counter();

    FalseTestSheetValidator falseTestSheetValidator = new FalseTestSheetValidator(counter);

    DefaultSheetValidationHelperExceptionTest.TestCellValidator testCellValidator7 = new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter);
    testCellValidator7.group("double1");
    testCellValidator7.matchField("double1");

    SheetValidationHelper sheetValidationHelper1 = new DefaultSheetValidationHelper();
    sheetValidationHelper1.addDependencyValidator(testCellValidator7);


    sheetValidationHelper1.addSheetValidator(falseTestSheetValidator);

    boolean valid = sheetValidationHelper1.valid(sheet, sheetMeta);

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

    DefaultSheetValidationHelperExceptionTest.Counter counter = new DefaultSheetValidationHelperExceptionTest.Counter();

    Sheet sheet = getSheet();

    DefaultSheetValidationHelper defaultSheetValidationHelper = new DefaultSheetValidationHelper();

    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter).group("int1").matchField("int1"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter).group("int2").matchField("int2").dependsOn("int1"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationHelperExceptionTest.TestMultiValidator(counter).group("int1").matchFields("long1", "long2"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter).group("string").matchField("string"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter).group("float1").matchField("float1").dependsOn("int2"));
    defaultSheetValidationHelper.addDependencyValidator(
        new DefaultSheetValidationHelperExceptionTest.TestCellValidator(counter).group("float2").matchField("float2").dependsOn("int2"));

    boolean valid = defaultSheetValidationHelper.valid(sheet, sheetMeta);

    assertTrue(valid);
    assertEquals(counter.hitTime(), 4);
  }

  private class TrueTestRowValidator implements RowValidator {

    private DefaultSheetValidationHelperExceptionTest.Counter counter;

    private Set<String> messageOnFields = new HashSet<>();

    TrueTestRowValidator(DefaultSheetValidationHelperExceptionTest.Counter counter, String... messageOnFields) {
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

    private DefaultSheetValidationHelperExceptionTest.Counter counter;

    private Set<String> messageOnFields = new HashSet<>();

    FalseTestRowValidator(DefaultSheetValidationHelperExceptionTest.Counter counter, String... messageOnFields) {
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

    private DefaultSheetValidationHelperExceptionTest.Counter counter;

    TrueTestSheetValidator(DefaultSheetValidationHelperExceptionTest.Counter counter) {
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

    private DefaultSheetValidationHelperExceptionTest.Counter counter;

    FalseTestSheetValidator(DefaultSheetValidationHelperExceptionTest.Counter counter) {
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
