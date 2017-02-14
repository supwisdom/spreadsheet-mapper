package com.supwisdom.spreadsheet.mapper.validation.builder;

import com.supwisdom.spreadsheet.mapper.validation.builder.cell.*;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.UnionUniqueValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by hanwen on 2017/1/23.
 */
public class CellValidatorBatchBuilderTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CellValidatorBatchBuilderTest.class);

  @BeforeClass
  public void before() {
    LOGGER.debug("-------------------starting test cell validator batch builder-------------------");
  }

  @Test
  public void testBuilder() throws Exception {

    CellValidatorBatchBuilder builder = new CellValidatorBatchBuilder();

    List validators = builder
        .start(BooleanValidatorFactory.getInstance())
        .errorMessage("test")
        .matchFields("t1")
        .group("t1")
        .dependsOn("t2", "t3")
        .param(
            new BooleanParam()
                .trueStrings("y")
                .falseStrings("n")
        )
        .end()

        .start(NumberScaleRangeValidatorFactory.getInstance())
        .errorMessage("test")
        .group("t2")
        .matchFields("t2")
        .param(
            new NumberScaleRangeParam()
                .gte(0)
                .lte(2)
        )
        .end()

        .start(UnionUniqueValidatorFactory.getInstance())
        .group("t4")
        .dependsOn("t1")
        .errorMessage("test")
        .matchFields("t4", "t5")
        .end()

        .start(DigitsValidatorFactory.getInstance())
        .matchFields("t6", "t7")
        .end()

        .start(RequireValidatorFactory.getInstance())
        .matchFields("t1", "t2", "t3", "t4")
        .end()

        .start(NumberValidatorFactory.getInstance())
        .matchFields("t8")
        .dependsOn("t7")
        .errorMessage("test")
        .end()

        .start(RegexFormatValidatorFactory.getInstance())
        .matchFields("t9")
        .errorMessage("test")
        .group("t9")
        .dependsOn("t8")
        .param("^[1-9]\\d*$")
        .end()

        .start(UniqueValidatorFactory.getInstance())
        .matchFields("t11")
        .end()

        .build();

    assertEquals(validators.size(), 12);

    {
      CellValidator v1 = (CellValidator) validators.get(0);
      assertEquals(v1.getGroup(), "t1");
      assertEquals(v1.getMatchField(), "t1");
      assertEquals(v1.getErrorMessage(), "test");
      assertEquals(v1.getDependsOn(), new LinkedHashSet<>(Arrays.asList("t2", "t3")));
    }

    {
      CellValidator v2 = (CellValidator) validators.get(1);
      assertEquals(v2.getGroup(), "t2");
      assertEquals(v2.getMatchField(), "t2");
      assertEquals(v2.getErrorMessage(), "test");
      assertEquals(v2.getDependsOn().size(), 0);
    }

    {
      UnionCellValidator v4 = (UnionCellValidator) validators.get(2);
      assertEquals(v4.getGroup(), "t4");
      assertEquals(v4.getMatchFields(), new LinkedHashSet<>(Arrays.asList("t4", "t5")));
      assertEquals(v4.getErrorMessage(), "test");
      assertEquals(v4.getDependsOn(), new LinkedHashSet<>(Collections.singleton("t1")));
    }

    {
      CellValidator v5 = (CellValidator) validators.get(3);
      assertEquals(v5.getGroup(), "t6");
      assertEquals(v5.getMatchField(), "t6");
      assertEquals(v5.getDependsOn().size(), 0);
      assertNull(v5.getErrorMessage());
    }

    {
      CellValidator v6 = (CellValidator) validators.get(4);
      assertEquals(v6.getGroup(), "t7");
      assertEquals(v6.getMatchField(), "t7");
      assertEquals(v6.getDependsOn().size(), 0);
      assertNull(v6.getErrorMessage());
    }

    {
      CellValidator v7 = (CellValidator) validators.get(5);
      assertEquals(v7.getGroup(), "t1");
      assertEquals(v7.getMatchField(), "t1");
      assertEquals(v7.getDependsOn().size(), 0);
      assertNull(v7.getErrorMessage());
    }

    {
      CellValidator v8 = (CellValidator) validators.get(6);
      assertEquals(v8.getGroup(), "t2");
      assertEquals(v8.getMatchField(), "t2");
      assertEquals(v8.getDependsOn().size(), 0);
      assertNull(v8.getErrorMessage());
    }

    {
      CellValidator v9 = (CellValidator) validators.get(7);
      assertEquals(v9.getGroup(), "t3");
      assertEquals(v9.getMatchField(), "t3");
      assertEquals(v9.getDependsOn().size(), 0);
      assertNull(v9.getErrorMessage());
    }

    {
      CellValidator v10 = (CellValidator) validators.get(8);
      assertEquals(v10.getGroup(), "t4");
      assertEquals(v10.getMatchField(), "t4");
      assertEquals(v10.getDependsOn().size(), 0);
      assertNull(v10.getErrorMessage());
    }

    {
      CellValidator v11 = (CellValidator) validators.get(9);
      assertEquals(v11.getGroup(), "t8");
      assertEquals(v11.getMatchField(), "t8");
      assertEquals(v11.getErrorMessage(), "test");
      assertEquals(v11.getDependsOn(), new LinkedHashSet<>(Collections.singleton("t7")));
    }

    {
      CellValidator v12 = (CellValidator) validators.get(10);
      assertEquals(v12.getGroup(), "t9");
      assertEquals(v12.getMatchField(), "t9");
      assertEquals(v12.getErrorMessage(), "test");
      assertEquals(v12.getDependsOn(), new LinkedHashSet<>(Collections.singleton("t8")));
    }


    {
      CellValidator v14 = (CellValidator) validators.get(11);
      assertEquals(v14.getGroup(), "t11");
      assertEquals(v14.getMatchField(), "t11");
      assertNull(v14.getErrorMessage());
      assertEquals(v14.getDependsOn().size(), 0);
    }

  }

}
