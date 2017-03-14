package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/19.
 */
@Test
public class DefaultObject2WorkbookComposerTest {

  @Test
  public void testCompose() throws Exception {

    Object2WorkbookComposer object2WorkbookComposer = new DefaultObject2WorkbookComposer();

    Object2SheetComposer<Foo> object2SheetComposer = new DefaultObject2SheetComposer<>();

    SheetMeta sheetMeta1 = new SheetMetaBean("sheet-a", 1);
    SheetMeta sheetMeta2 = new SheetMetaBean("sheet-b", 1);

    WorkbookMeta workbookMeta = new WorkbookMetaBean();
    workbookMeta.addSheetMeta(sheetMeta2);
    workbookMeta.addSheetMeta(sheetMeta1);

    List<List> dataOfSheets = new ArrayList<>();
    dataOfSheets.add(Collections.emptyList());
    dataOfSheets.add(Collections.emptyList());

    Workbook workbook = object2WorkbookComposer
        .addObject2SheetComposer(object2SheetComposer)
        .addObject2SheetComposer(object2SheetComposer)
        .compose(dataOfSheets, workbookMeta);

    assertEquals(workbook.sizeOfSheets(), 2);
    assertEquals(workbook.getSheet(1).getName(), "sheet-b");
    assertEquals(workbook.getSheet(2).getName(), "sheet-a");

  }
}
