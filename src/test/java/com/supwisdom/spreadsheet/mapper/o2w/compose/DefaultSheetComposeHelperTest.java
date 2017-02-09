package com.supwisdom.spreadsheet.mapper.o2w.compose;

import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.compose.converter.buildin.BooleanConverter;
import com.supwisdom.spreadsheet.mapper.o2w.compose.converter.buildin.PlainNumberConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.o2w.compose.converter.buildin.LocalDateTimeConverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/5.
 */
@Test(groups = "sheetComposeHelperTest")
public class DefaultSheetComposeHelperTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSheetComposeHelperTest.class);

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

    SheetComposeHelper<TestBean> sheetComposeHelper1 = new DefaultSheetComposeHelper<TestBean>();
    addConverters(sheetComposeHelper1);

    Sheet sheet1 = sheetComposeHelper1.compose(data, sheetMeta1);

    AssertUtil.assertSheetEquals(sheet1, true);

    SheetMeta sheetMeta2 = TestFactory.createSheetMeta(false);

    SheetComposeHelper<TestBean> sheetComposeHelper2 = new DefaultSheetComposeHelper<TestBean>();
    addConverters(sheetComposeHelper2);

    Sheet sheet2 = sheetComposeHelper2.compose(data, sheetMeta2);

    AssertUtil.assertSheetEquals(sheet2, false);

    SheetComposeHelper<TestBean> sheetComposeHelper3 = new DefaultSheetComposeHelper<TestBean>();

    Sheet sheet3 = sheetComposeHelper3.compose(Collections.<TestBean>emptyList(), sheetMeta1);
    assertEquals(sheet3.sizeOfRows(), 1);
    AssertUtil.assertHeaderRowEquals(sheet3.getRow(1), true);
  }

  static void addConverters(SheetComposeHelper<TestBean> sheetComposeHelper) {

    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("int1"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("int2"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("long1"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("long2"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("float1"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("float2"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("double1"));
    sheetComposeHelper.addFieldConverter(new PlainNumberConverter<TestBean>().matchField("double2"));
    sheetComposeHelper.addFieldConverter(new BooleanConverter<TestBean>().matchField("boolean1").trueString("pass").falseString("failure"));
    sheetComposeHelper.addFieldConverter(new BooleanConverter<TestBean>().matchField("boolean2").trueString("pass").falseString("failure"));
    sheetComposeHelper.addFieldConverter(new LocalDateTimeConverter<TestBean>().matchField("localDateTime").pattern("yyyy-MM-dd HH:mm:ss"));

  }
}
