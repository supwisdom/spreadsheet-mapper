package com.supwisdom.spreadsheet.mapper.validation.validator.unioncell;

import com.supwisdom.spreadsheet.mapper.model.core.CellBean;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by qianjia on 2017/2/14.
 */
public class LambdaUnionCellValidatorTest {
  @Test
  public void testDoValidate() throws Exception {

    LambdaUnionCellValidator validator = new LambdaUnionCellValidator((cell, fieldMeta) -> false);
    assertFalse(validator.validate(
        Collections.singletonList(new CellBean("sth")),
        Collections.singletonList(new FieldMetaBean("a", 1)))
    );

    LambdaUnionCellValidator validator2 = new LambdaUnionCellValidator((cell, fieldMeta) -> true);
    assertTrue(validator2.validate(
        Collections.singletonList(new CellBean("sth")),
        Collections.singletonList(new FieldMetaBean("a", 1)))
    );
  }

}
