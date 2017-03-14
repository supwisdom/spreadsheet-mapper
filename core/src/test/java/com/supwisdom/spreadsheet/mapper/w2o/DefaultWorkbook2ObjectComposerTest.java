package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.core.WorkbookBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test
public class DefaultWorkbook2ObjectComposerTest {

  @Test
  public void testComposeMultipleSheetsWorkbook() throws Exception {

    Workbook2ObjectComposer workbook2ObjectComposer = new DefaultWorkbook2ObjectComposer();

    Sheet2ObjectComposer<Foo> sheet2ObjectComposer =
        new DefaultSheet2ObjectComposer<Foo>().setObjectFactory(new FooFactory());

    Workbook workbook = new WorkbookBean();
    workbook.addSheet(new SheetBean("sheet-1"));
    workbook.addSheet(new SheetBean("sheet-2"));

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(new SheetMetaBean("sheet-1", 1));
    workbookMeta.addSheetMeta(new SheetMetaBean("sheet-2", 1));

    List<List> list = workbook2ObjectComposer
        .addSheet2ObjectComposer(sheet2ObjectComposer)
        .addSheet2ObjectComposer(sheet2ObjectComposer)
        .compose(workbook, workbookMeta);

    assertEquals(list.size(), 2);

  }

}
