package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.core.RowBean;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.validation.validator.*;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/3/13.
 */
public class DefaultSheetValidationJobTest {

  @DataProvider
  public Object[][] provideSheetAndMeta() {

    Sheet sheet = new SheetBean("Sheet-1");

    RowBean row1 = new RowBean();
    sheet.addRow(row1);

    CellBean cell11 = new CellBean("code-1");
    CellBean cell12 = new CellBean("name-1");

    row1.addCell(cell11);
    row1.addCell(cell12);

    RowBean row2 = new RowBean();
    sheet.addRow(row2);

    CellBean cell21 = new CellBean("code-2");
    CellBean cell22 = new CellBean("name-2");

    row2.addCell(cell21);
    row2.addCell(cell22);

    SheetMeta sheetMeta = new SheetMetaBean(1);
    FieldMeta fieldMeta1 = new FieldMetaBean("code", 1);
    FieldMeta fieldMeta2 = new FieldMetaBean("name", 2);
    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);

    return new Object[][] {
        new Object[] { sheet, sheetMeta }
    };
  }

  /**
   * 测试 SheetValidator, RowValidator, CellValidator, UnionValidator 校验顺序
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testDifferentKindValidatorExecutionOrder(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(new ExecRecordSheetValidator("SheetValidator", true, executionRecorder));
    validationJob.addValidator(new ExecRecordRowValidator("RowValidator", true, executionRecorder));
    validationJob.addValidator(
        new ExecRecordCellValidator("CellValidator1", true, executionRecorder).matchField("name").group("groupA"));
    validationJob.addValidator(
        new ExecRecordUnionCellValidator("UnionCellValidator", true, executionRecorder).matchFields("name", "code")
            .group("groupB"));
    validationJob.addValidator(
        new ExecRecordCellValidator("CellValidator2", true, executionRecorder).matchField("name").group("groupA"));

    validationJob.validate(sheet, sheetMeta);

    /*
     执行顺序是这样的：
     1. SheetValidator
     2. RowValidator（每行）
     3. Dependant组，按照组的添加顺序来执行（每行）
     */
    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "SheetValidator#validate,RowValidator#validate,CellValidator1#validate,CellValidator2#validate,UnionCellValidator#validate,"
            +
            "RowValidator#validate,CellValidator1#validate,CellValidator2#validate,UnionCellValidator#validate"
    );

  }

  /**
   * 测试 SheetValidator 失败后，后面的校验器不执行的情况。
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testSheetValidatorShortCircuit(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(new ExecRecordSheetValidator("SheetValidator", false, executionRecorder));
    validationJob.addValidator(new ExecRecordRowValidator("RowValidator", true, executionRecorder));
    validationJob.addValidator(
        new ExecRecordCellValidator("CellValidator1", true, executionRecorder).matchField("name").group("groupA"));
    validationJob.addValidator(
        new ExecRecordUnionCellValidator("UnionCellValidator", true, executionRecorder).matchFields("name", "code")
            .group("groupB"));
    validationJob.addValidator(
        new ExecRecordCellValidator("CellValidator2", true, executionRecorder).matchField("name").group("groupA"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "SheetValidator#validate"
    );

  }

  /**
   * 测试 RowValidator 失败后，后面的校验器不执行的情况。
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testRowValidatorShortCircuit(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(new ExecRecordSheetValidator("SheetValidator", true, executionRecorder));
    validationJob.addValidator(new ExecRecordRowValidator("RowValidator", false, executionRecorder));
    validationJob.addValidator(
        new ExecRecordCellValidator("CellValidator1", true, executionRecorder).matchField("name").group("groupA"));
    validationJob.addValidator(
        new ExecRecordUnionCellValidator("UnionCellValidator", true, executionRecorder).matchFields("name", "code")
            .group("groupB"));
    validationJob.addValidator(
        new ExecRecordCellValidator("CellValidator2", true, executionRecorder).matchField("name").group("groupA"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "SheetValidator#validate,RowValidator#validate,RowValidator#validate"
    );

  }

  /**
   * 测试组和组之间的如果有依赖的化，他们的执行顺序
   *
   * @param sheet
   * @param sheetMeta
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testDependantGroupExecutionOrder(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(
        new ExecRecordCellValidator("group4-1", true, executionRecorder).matchField("name").group("group4")
            .dependsOn("groupA", "groupC"));

    validationJob.addValidator(
        new ExecRecordCellValidator("groupB-1", true, executionRecorder).matchField("name").group("groupB")
            .dependsOn("groupA"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupB-2", true, executionRecorder).matchField("name").group("groupB")
            .dependsOn("groupA"));

    validationJob.addValidator(
        new ExecRecordCellValidator("groupA-1", true, executionRecorder).matchField("name").group("groupA"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupA-2", true, executionRecorder).matchField("name").group("groupA"));

    validationJob.addValidator(
        new ExecRecordCellValidator("groupC-1", true, executionRecorder).matchField("name").group("groupC"));

    validationJob.validate(sheet, sheetMeta);

    /*
      执行顺序是：按照添加的顺序来，如果有依赖别人的，递归执行被依赖方的校验
     */
    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "groupA-1#validate,groupA-2#validate,groupC-1#validate,group4-1#validate,groupB-1#validate,groupB-2#validate,"
            + "groupA-1#validate,groupA-2#validate,groupC-1#validate,group4-1#validate,groupB-1#validate,groupB-2#validate"
    );

  }

  /**
   * 测试 同组的 Dependant 如果有一个失败了，后面的校验器不执行的情况。
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testDependantGroupInnerShortCircuit(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(
        new ExecRecordCellValidator("groupA-1", false, executionRecorder).matchField("name").group("groupA"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupA-2", true, executionRecorder).matchField("name").group("groupA"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "groupA-1#validate,groupA-1#validate"
    );

  }

  /**
   * 测试 不同组的 Dependant 一个失败不影响另一个的执行
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testDifferentDependantGroupFailNoInfluence(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(
        new ExecRecordCellValidator("groupA", false, executionRecorder).matchField("name").group("groupA"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupB", true, executionRecorder).matchField("name").group("groupB"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "groupA#validate,groupB#validate," + "groupA#validate,groupB#validate"
    );

  }

  /**
   * 测试 A组依赖B组，B组依赖C组，C组失败后，A、B组也就不执行的情况。
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testSingleChainDependencyShortCircuit(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(
        new ExecRecordCellValidator("groupA", true, executionRecorder).matchField("name").group("groupA")
            .dependsOn("groupB"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupB", true, executionRecorder).matchField("name").group("groupB")
            .dependsOn("groupC"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupC", false, executionRecorder).matchField("name").group("groupC"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "groupC#validate,groupC#validate"
    );

  }

  /**
   * 测试 A组依赖B、C组，C组失败后，A组也就不执行的情况。
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testMultiDependencyShortCircuit(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(
        new ExecRecordCellValidator("groupA", true, executionRecorder).matchField("name").group("groupA")
            .dependsOn("groupB", "groupC"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupB", true, executionRecorder).matchField("name").group("groupB"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupC", false, executionRecorder).matchField("name").group("groupC"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "groupB#validate,groupC#validate,groupB#validate,groupC#validate"
    );

  }

  /**
   * 测试 A组依赖B、C组，C组依赖D、E组，E组失败后，A、C组也就不执行的情况。
   */
  @Test(dataProvider = "provideSheetAndMeta")
  public void testMultiDependencyChainShortCircuit(Sheet sheet, SheetMeta sheetMeta) {

    DefaultSheetValidationJob validationJob = new DefaultSheetValidationJob();
    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    validationJob.addValidator(
        new ExecRecordCellValidator("groupA", true, executionRecorder).matchField("name").group("groupA")
            .dependsOn("groupB", "groupC"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupB", true, executionRecorder).matchField("name").group("groupB"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupC", true, executionRecorder).matchField("name").group("groupC")
            .dependsOn("groupD", "groupE"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupD", true, executionRecorder).matchField("name").group("groupD"));
    validationJob.addValidator(
        new ExecRecordCellValidator("groupE", false, executionRecorder).matchField("name").group("groupE"));

    validationJob.validate(sheet, sheetMeta);

    assertEquals(
        StringUtils.join(executionRecorder.getExecutions(), ','),
        "groupB#validate,groupD#validate,groupE#validate,groupB#validate,groupD#validate,groupE#validate"
    );

  }

}


