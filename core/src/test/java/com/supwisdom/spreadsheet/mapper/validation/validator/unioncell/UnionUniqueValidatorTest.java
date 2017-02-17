package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2017/1/4.
 */
public class UnionUniqueValidatorTest {

  private UnionUniqueValidator validator = new UnionUniqueValidator();

  @DataProvider
  public Object[][] testDoValidateParam() {

    return new Object[][] {
        new Object[] { new String[] { "a" }, true },
        new Object[] { new String[] { "a" }, false },
        new Object[] { new String[] { "a", "b" }, true },
        new Object[] { new String[] { "a", "c" }, true },
        new Object[] { new String[] { "a", "b" }, false },
        new Object[] { new String[] { "a", null }, true },
        new Object[] { new String[] { "a", null }, false }
    };
  }

  @Test(dataProvider = "testDoValidateParam")
  public void testDoValidate(String[] cellValues, boolean expected) throws Exception {

    List<Cell> cellBeans = new ArrayList<>();
    for (String cellValue : cellValues) {
      cellBeans.add(new CellBean(cellValue));
    }

    boolean valid = validator.doValidate(cellBeans, Collections.<FieldMeta>emptyList());
    assertEquals(valid, expected);

  }

}
