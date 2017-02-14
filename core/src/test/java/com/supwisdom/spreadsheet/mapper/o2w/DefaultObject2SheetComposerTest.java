package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.BooleanConverter;
import com.supwisdom.spreadsheet.mapper.o2w.converter.PlainNumberConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "defaultObject2SheetComposerTest")
public class DefaultObject2SheetComposerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultObject2SheetComposerTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test sheet compose helper-------------------");
  }

  @Test
  public void testCompose() throws Exception {

    SheetMeta sheetMeta1 = TestFactory.createSheetMeta(true);

    TestBean testBean1 = TestFactory.createBean1();
    TestBean testBean2 = TestFactory.createBean2();

    List<TestBean> data = Arrays.asList(testBean1, testBean2);

    Object2SheetComposer<TestBean> object2SheetComposer1 = new DefaultObject2SheetComposer<TestBean>();
    addConverters(object2SheetComposer1);

    Sheet sheet1 = object2SheetComposer1.compose(data, sheetMeta1);

    AssertUtil.assertSheetEquals(sheet1, true);

    SheetMeta sheetMeta2 = TestFactory.createSheetMeta(false);

    Object2SheetComposer<TestBean> object2SheetComposer2 = new DefaultObject2SheetComposer<TestBean>();
    addConverters(object2SheetComposer2);

    Sheet sheet2 = object2SheetComposer2.compose(data, sheetMeta2);

    AssertUtil.assertSheetEquals(sheet2, false);

    Object2SheetComposer<TestBean> object2SheetComposer3 = new DefaultObject2SheetComposer<TestBean>();

    Sheet sheet3 = object2SheetComposer3.compose(Collections.<TestBean>emptyList(), sheetMeta1);
    assertEquals(sheet3.sizeOfRows(), 1);
    AssertUtil.assertHeaderRowEquals(sheet3.getRow(1), true);
  }

  static void addConverters(Object2SheetComposer<TestBean> object2SheetComposer) {

    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("int1"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("int2"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("long1"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("long2"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("float1"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("float2"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("double1"));
    object2SheetComposer.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("double2"));
    object2SheetComposer.addFieldConverter(new BooleanConverter<TestBean>().matchField("boolean1").trueString("pass").falseString("failure"));
    object2SheetComposer.addFieldConverter(new BooleanConverter<TestBean>().matchField("boolean2").trueString("pass").falseString("failure"));

  }
}
