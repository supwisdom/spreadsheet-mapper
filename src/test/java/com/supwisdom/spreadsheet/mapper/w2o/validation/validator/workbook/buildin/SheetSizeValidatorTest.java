package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.workbook.buildin;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/4.
 */
public class SheetSizeValidatorTest {

  @Test
  public void testValid() throws Exception {

    SheetSizeValidator sheetSizeValidator = new SheetSizeValidator().size(2).errorMessage("test").messageOnSheet(1);

    Workbook workbook = TestFactory.createWorkbook();

    assertFalse(sheetSizeValidator.valid(workbook, new WorkbookMetaBean()));

    workbook.addSheet(new SheetBean());

    assertTrue(sheetSizeValidator.valid(workbook, new WorkbookMetaBean()));

  }

}
