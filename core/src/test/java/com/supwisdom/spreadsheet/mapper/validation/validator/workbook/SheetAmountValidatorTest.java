package com.supwisdom.spreadsheet.mapper.validation.validator.workbook;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.core.SheetBean;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/4.
 */
public class SheetAmountValidatorTest {

  @Test
  public void testValidate() throws Exception {

    SheetAmountValidator sheetAmountValidator = new SheetAmountValidator(2);

    Workbook workbook = TestFactory.createWorkbook();

    assertFalse(sheetAmountValidator.validate(workbook, new WorkbookMetaBean()));

    workbook.addSheet(new SheetBean());

    assertTrue(sheetAmountValidator.validate(workbook, new WorkbookMetaBean()));

  }

}
