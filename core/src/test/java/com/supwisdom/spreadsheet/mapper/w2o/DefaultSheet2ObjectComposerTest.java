package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.ExecutionRecorder;
import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.*;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.w2o.listener.ExecRecordCellProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.ExecRecordRowProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.ExecRecordSheetProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.setter.ExecRecordPropertySetter;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "defaultSheet2ObjectComposerTest")
public class DefaultSheet2ObjectComposerTest {

  @DataProvider
  public Object[][] provideSheetAndMeta() {

    Sheet sheet = new SheetBean();

    RowBean row1 = new RowBean();
    row1.addCell(new CellBean("11"));
    row1.addCell(new CellBean("12"));
    sheet.addRow(row1);

    RowBean row2 = new RowBean();
    row2.addCell(new CellBean("21"));
    row2.addCell(new CellBean("22"));
    sheet.addRow(row2);

    SheetMeta sheetMeta = new SheetMetaBean("SheAt", 1);
    FieldMetaBean fieldMeta1 = new FieldMetaBean("int1", 1);
    sheetMeta.addFieldMeta(fieldMeta1);

    FieldMetaBean fieldMeta2 = new FieldMetaBean("long1", 2);
    sheetMeta.addFieldMeta(fieldMeta2);

    return new Object[][] {
        new Object[] { sheet, sheetMeta }
    };

  }

  @Test(dataProvider = "provideSheetAndMeta")
  public void testListenerExecutionOrder(Sheet sheet, SheetMeta sheetMeta) throws Exception {

    ExecutionRecorder executionRecorder = new ExecutionRecorder();
    Sheet2ObjectComposer<Foo> composer = new DefaultSheet2ObjectComposer<Foo>()
        .setObjectFactory(new FooFactory())
        .setSheetProcessorListener(new ExecRecordSheetProcessListener(executionRecorder))
        .setRowProcessorListener(new ExecRecordRowProcessListener(executionRecorder))
        .setCellProcessorListener(new ExecRecordCellProcessListener(executionRecorder));

    composer.compose(sheet, sheetMeta);

    List<String> executions = executionRecorder.getExecutions();
    assertEquals(
        StringUtils.join(executions, ','),
        "sheet-listener#before[SheAt],"

            + "row-listener#before[SheAt,1],"
            + "cell-listener#before[int1,1,1],cell-listener#after[int1,1,1],cell-listener#before[long1,1,2],cell-listener#after[long1,1,2],"
            + "row-listener#after[SheAt,1],"

            + "row-listener#before[SheAt,2],"
            + "cell-listener#before[int1,2,1],cell-listener#after[int1,2,1],cell-listener#before[long1,2,2],cell-listener#after[long1,2,2],"
            + "row-listener#after[SheAt,2],"

            + "sheet-listener#after[SheAt]"
    );

  }

  @Test(dataProvider = "provideSheetAndMeta")
  public void testPropertySetterExecutionOrder(Sheet sheet, SheetMeta sheetMeta) throws Exception {

    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    Sheet2ObjectComposer<Foo> composer = new DefaultSheet2ObjectComposer<>();
    composer.setObjectFactory(new FooFactory());
    composer.addPropertySetter(new ExecRecordPropertySetter(executionRecorder).matchField("int1"));
    composer.addPropertySetter(new ExecRecordPropertySetter(executionRecorder).matchField("long1"));

    composer.compose(sheet, sheetMeta);

    List<String> executions = executionRecorder.getExecutions();
    assertEquals(StringUtils.join(executions, ','), "property-setter#setProperty[int1,1,1],property-setter#setProperty[long1,1,2],property-setter#setProperty[int1,2,1],property-setter#setProperty[long1,2,2]");

  }

  @Test(expectedExceptions = Workbook2ObjectComposeException.class, expectedExceptionsMessageRegExp = ".*SheetMeta contains duplicate FieldMeta.*")
  public void testDuplicateFieldMeta() {

    Sheet2ObjectComposer<Foo> composer = new DefaultSheet2ObjectComposer<>();
    composer.setObjectFactory(new FooFactory());

    SheetBean sheetBean = new SheetBean();
    SheetMetaBean sheetMetaBean = new SheetMetaBean(1);

    FieldMetaBean fieldMeta1 = new FieldMetaBean("a", 1);
    FieldMetaBean fieldMeta2 = new FieldMetaBean("a", 2);

    sheetMetaBean.addFieldMeta(fieldMeta1);
    sheetMetaBean.addFieldMeta(fieldMeta2);

    composer.compose(sheetBean, sheetMetaBean);
  }

  @Test(dataProvider = "provideSheetAndMeta")
  public void testIgnoreFields(Sheet sheet, SheetMeta sheetMeta) {

    ExecutionRecorder executionRecorder = new ExecutionRecorder();

    Sheet2ObjectComposer<Foo> composer = new DefaultSheet2ObjectComposer<>();
    composer.ignoreFields("int1");

    composer.setObjectFactory(new FooFactory());
    composer.addPropertySetter(new ExecRecordPropertySetter(executionRecorder).matchField("int1"));
    composer.addPropertySetter(new ExecRecordPropertySetter(executionRecorder).matchField("long1"));

    composer.compose(sheet, sheetMeta);

    List<String> executions = executionRecorder.getExecutions();
    assertEquals(StringUtils.join(executions, ','), "property-setter#setProperty[long1,1,2],property-setter#setProperty[long1,2,2]");

  }
}
