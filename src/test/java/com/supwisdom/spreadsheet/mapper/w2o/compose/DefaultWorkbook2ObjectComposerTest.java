package com.supwisdom.spreadsheet.mapper.w2o.compose;

import com.supwisdom.spreadsheet.mapper.TestBean;
import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.WorkbookBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.AssertUtil;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static com.supwisdom.spreadsheet.mapper.w2o.compose.DefaultSheet2ObjectComposerTest.addSetter;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test(dependsOnGroups = "sheetProcessHelperTest")
public class DefaultWorkbook2ObjectComposerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWorkbook2ObjectComposerTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test workbook process helper-------------------");
  }

  @Test
  public void testProcess() throws Exception {

    Workbook2ObjectComposer workbook2ObjectComposer = new DefaultWorkbook2ObjectComposer();

    Sheet2ObjectComposer<TestBean> sheet2ObjectComposer =
        new DefaultSheet2ObjectComposer<TestBean>()
            .setObjectFactory(new DefaultSheet2ObjectComposerTest.TestBeanObjectFactory());

    addSetter(sheet2ObjectComposer);

    Workbook workbook = new WorkbookBean();
    workbook.addSheet(TestFactory.createSheet());
    workbook.addSheet(TestFactory.createSheet());

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));
    workbookMeta.addSheetMeta(TestFactory.createSheetMeta(true));

    List<List> list = workbook2ObjectComposer
        .addSheet2ObjectComposer(sheet2ObjectComposer)
        .addSheet2ObjectComposer(sheet2ObjectComposer)
        .compose(workbook, workbookMeta);

    assertEquals(list.size(), 2);

    for (List sub : list) {
      assertEquals(sub.size(), 2);

      AssertUtil.assertTestBean1Equals((TestBean) sub.get(0));
      AssertUtil.assertTestBean2Equals((TestBean) sub.get(1));
    }
  }

}
