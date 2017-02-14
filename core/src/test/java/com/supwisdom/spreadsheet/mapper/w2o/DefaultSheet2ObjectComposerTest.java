package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.w2o.listener.CellProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.RowProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.SheetProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.setter.BooleanSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "defaultSheet2ObjectComposerTest")
public class DefaultSheet2ObjectComposerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSheet2ObjectComposerTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test sheet process helper-------------------");
  }

  @Test
  public void testProcess() throws Exception {

    Sheet sheet = TestFactory.createSheet();
    SheetMeta sheetMeta1 = TestFactory.createSheetMeta(true);

    Counter counter = new Counter();
    Sheet2ObjectComposer<TestBean> processor1 = new DefaultSheet2ObjectComposer<TestBean>()
        .setObjectFactory(new TestBeanObjectFactory())
        .setSheetProcessorListener(new TestSheetProcessListener(counter))
        .setRowProcessorListener(new TestRowProcessListener(counter))
        .setCellProcessorListener(new TestCellProcessListener(counter));

    addSetter(processor1);

    List<TestBean> list1 = processor1.compose(sheet, sheetMeta1);

    // 2 sheet, 2 row, 12 columns
    assertEquals(counter.hitTime(), 2 + 2 * 2 + 12 * 2 * 2);

    assertEquals(list1.size(), 2);

    AssertUtil.assertTestBean1Equals(list1.get(0));
    AssertUtil.assertTestBean2Equals(list1.get(1));

    SheetMeta sheetMeta2 = new SheetMetaBean(sheetMeta1.getDataStartRowIndex());

    Sheet2ObjectComposer<TestBean> processor2 = new DefaultSheet2ObjectComposer<TestBean>()
        .setObjectFactory(new TestBeanObjectFactory());

    List<TestBean> list2 = processor2.compose(sheet, sheetMeta2);

    assertEquals(list2.size(), 2);
    for (TestBean testBean : list2) {
      AssertUtil.assertTestBeanNull(testBean);
    }
  }

  static void addSetter(Sheet2ObjectComposer<TestBean> processor1) {
    processor1.addFieldSetter(new BooleanSetter(
        Collections.singleton("pass"),
        Collections.singleton("failure")
    ).matchField("boolean1"));
    processor1.addFieldSetter(new BooleanSetter(
        Collections.singleton("pass"),
        Collections.singleton("failure")
    )
    .matchField("boolean2"));

  }

  static class TestBeanObjectFactory implements ObjectFactory<TestBean> {

    @Override
    public TestBean create(Row row, SheetMeta sheetMeta) {
      return new TestBean();
    }
  }

  private class TestSheetProcessListener implements SheetProcessListener<TestBean> {

    private Counter counter;

    public TestSheetProcessListener(Counter counter) {
      this.counter = counter;
    }

    @Override
    public void before(Sheet sheet, SheetMeta sheetMeta) {
      counter.hit();
    }

    @Override
    public void after(List<TestBean> objects, Sheet sheet, SheetMeta sheetMeta) {
      counter.hit();
    }
  }

  private class TestRowProcessListener implements RowProcessListener<TestBean> {

    private Counter counter;

    public TestRowProcessListener(Counter counter) {
      this.counter = counter;
    }

    @Override
    public void before(TestBean object, Row row, SheetMeta sheetMeta) {
      counter.hit();
    }

    @Override
    public void after(TestBean object, Row row, SheetMeta sheetMeta) {
      counter.hit();
    }
  }

  private class TestCellProcessListener implements CellProcessListener<TestBean> {

    private Counter counter;

    public TestCellProcessListener(Counter counter) {
      this.counter = counter;
    }

    @Override
    public void before(TestBean object, Cell cell, FieldMeta fieldMeta) {
      counter.hit();
    }

    @Override
    public void after(TestBean object, Cell cell, FieldMeta fieldMeta) {
      counter.hit();
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
}
