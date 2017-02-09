package com.supwisdom.spreadsheet.mapper.o2w.compose;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static com.supwisdom.spreadsheet.mapper.o2w.compose.DefaultSheetComposeHelperTest.addConverters;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test(dependsOnGroups = "sheetComposeHelperTest")
public class DefaultWorkbookComposeHelperTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWorkbookComposeHelperTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test workbook compose helper-------------------");
  }

  @Test
  public void testCompose() throws Exception {


    WorkbookComposeHelper workbookComposeHelper = new DefaultWorkbookComposeHelper();

    SheetComposeHelper<TestBean> sheetComposeHelper = new DefaultSheetComposeHelper<>();

    addConverters(sheetComposeHelper);

    TestBean bean1 = TestFactory.createBean1();
    TestBean bean2 = TestFactory.createBean2();

    List<List> objs = new ArrayList<>();
    objs.add(Arrays.asList(bean1, bean2));
    objs.add(Arrays.asList(bean1, bean2));

    SheetMeta sheetMeta1 = TestFactory.createSheetMeta(true);
    SheetMeta sheetMeta2 = TestFactory.createSheetMeta(false);

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(sheetMeta1);
    workbookMeta.addSheetMeta(sheetMeta2);

    assertEquals(sheetMeta1.getSheetIndex(), 1);
    assertEquals(sheetMeta2.getSheetIndex(), 2);

    Workbook workbook = workbookComposeHelper
        .addSheetComposeHelper(sheetComposeHelper)
        .addSheetComposeHelper(sheetComposeHelper)
        .compose(objs, workbookMeta);

    assertEquals(workbook.sizeOfSheets(), 2);
    AssertUtil.assertSheetEquals(workbook.getSheet(1), true);
    AssertUtil.assertSheetEquals(workbook.getSheet(2), false);
  }
}
