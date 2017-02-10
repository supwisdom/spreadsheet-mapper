package com.supwisdom.spreadsheet.mapper.validation.validator.sheet;

import com.supwisdom.spreadsheet.mapper.TestFactory;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import org.testng.annotations.Test;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;

import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by hanwen on 2017/1/4.
 */
public class FieldScopeValidatorTest {

  @Test
  public void testValid() throws Exception {

    Map<String, FieldMeta> fieldMetaMap = TestFactory.createFieldMetaMap();

    SheetMeta sheetMeta = TestFactory.createSheetMeta(true);

    Sheet sheet = TestFactory.createSheet();

    String[] fields1 = fieldMetaMap.keySet().toArray(new String[0]);

    FieldScopeValidator validator1 = new FieldScopeValidator().fieldScopes(fields1).errorMessage("");
    assertTrue(validator1.valid(sheet, sheetMeta));

    String[] fields2 = new String[]{"1", "2", "3"};
    FieldScopeValidator validator2 = new FieldScopeValidator().fieldScopes(fields2).errorMessage("");
    assertFalse(validator2.valid(sheet, sheetMeta));


    String[] fields3 = new String[fields1.length + 1];
    System.arraycopy(fields1, 0, fields3, 0, fields1.length);
    fields3[fields3.length - 1] = "1";

    FieldScopeValidator validator3 = new FieldScopeValidator().fieldScopes(fields3).errorMessage("");;
    assertTrue(validator3.valid(sheet, sheetMeta));
  }

}
