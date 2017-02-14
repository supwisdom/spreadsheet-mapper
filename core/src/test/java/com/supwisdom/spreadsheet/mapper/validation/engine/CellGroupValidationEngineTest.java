package com.supwisdom.spreadsheet.mapper.validation.engine;

import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.RequireValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionUniqueValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by qianjia on 2017/2/13.
 */
public class CellGroupValidationEngineTest {

  @DataProvider
  public Object[][] cellValidators() {

    SheetMetaBean sheetMetaBean = new SheetMetaBean("sheet", 2);
    sheetMetaBean.addFieldMeta(new FieldMetaBean("a.1", 1));
    sheetMetaBean.addFieldMeta(new FieldMetaBean("a.2", 2));
    sheetMetaBean.addFieldMeta(new FieldMetaBean("b.1", 3));
    sheetMetaBean.addFieldMeta(new FieldMetaBean("b.2", 4));
    sheetMetaBean.addFieldMeta(new FieldMetaBean("c.1", 5));
    List<Dependant> cellValidators = new ArrayList<>();

    /*
    group(a)
      require(a.1) depends on group(b)
      require(a.2) depends on group(c)
    group(b)
      require(b.1) depends on group(c)
      require(b.2) depends on -
    group(c)
      require(c.1) depends on -

     */
    cellValidators.add(new RequireValidator().group("a").matchField("a.1").dependsOn("b"));
    cellValidators.add(new RequireValidator().group("a").matchField("a.2").dependsOn("c"));
    cellValidators.add(new RequireValidator().group("b").matchField("b.1").dependsOn("c"));
    cellValidators.add(new RequireValidator().group("b").matchField("b.2"));
    cellValidators.add(new RequireValidator().group("c").matchField("c.1"));
    cellValidators.add(new UnionUniqueValidator().group("c").matchFields("b.2", "c.1"));

    return new Object[][] {
        new Object[] { sheetMetaBean, cellValidators }
    };
  }

  @Test(dataProvider = "cellValidators")
  public void testBuildCellValidatorGroups(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);

    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);
    assertEquals(cellValidatorGroups.size(), 3);
    assertEquals(cellValidatorGroups.get("a").size(), 2);
    assertEquals(cellValidatorGroups.get("b").size(), 2);
    assertEquals(cellValidatorGroups.get("c").size(), 2);

    assertEquals(((RequireValidator) cellValidatorGroups.get("a").get(0)).getMatchField(), "a.1");
    assertEquals(((RequireValidator) cellValidatorGroups.get("a").get(1)).getMatchField(), "a.2");

    assertEquals(((RequireValidator) cellValidatorGroups.get("b").get(0)).getMatchField(), "b.1");
    assertEquals(((RequireValidator) cellValidatorGroups.get("b").get(1)).getMatchField(), "b.2");

    assertEquals(((RequireValidator) cellValidatorGroups.get("c").get(0)).getMatchField(), "c.1");
    assertTrue(((UnionUniqueValidator) cellValidatorGroups.get("c").get(1)).getMatchFields().contains("b.2"));
    assertTrue(((UnionUniqueValidator) cellValidatorGroups.get("c").get(1)).getMatchFields().contains("c.1"));

  }

  @Test(dataProvider = "cellValidators", dependsOnMethods = "testBuildCellValidatorGroups")
  public void testBuildDependencyTree(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);
    LinkedHashMap<String, LinkedHashSet<String>> dependencyTree = engine.buildDependencyTree(cellValidatorGroups);

    assertEquals(dependencyTree.size(), 3);
    assertTrue(dependencyTree.get("a").contains("b"));
    assertTrue(dependencyTree.get("a").contains("c"));
    assertTrue(dependencyTree.get("b").contains("c"));
    assertEquals(dependencyTree.get("c").size(), 0);
  }

  @Test(dataProvider = "cellValidators", dependsOnMethods = "testBuildDependencyTree")
  public void testAssertNoMissingGroupGood(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);
    LinkedHashMap<String, LinkedHashSet<String>> dependencyTree = engine.buildDependencyTree(cellValidatorGroups);
    engine.assertNoMissingGroup(dependencyTree);

  }

  @Test(dataProvider = "cellValidators",
      dependsOnMethods = "testBuildDependencyTree",
      expectedExceptions = CellGroupValidationEngineException.class,
      expectedExceptionsMessageRegExp = "Group\\[a\\] depends on missing group\\(s\\)\\[d\\]"
  )
  public void testAssertNoMissingGroupBad(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    cellValidators.add(new RequireValidator().group("a").matchField("a.1").dependsOn("d"));

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);
    LinkedHashMap<String, LinkedHashSet<String>> dependencyTree = engine.buildDependencyTree(cellValidatorGroups);
    engine.assertNoMissingGroup(dependencyTree);

  }

  @Test(dataProvider = "cellValidators", dependsOnMethods = "testBuildDependencyTree")
  public void testAssertNoCyclicGood(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);
    LinkedHashMap<String, LinkedHashSet<String>> dependencyTree = engine.buildDependencyTree(cellValidatorGroups);
    engine.assertNoCyclic(dependencyTree);

  }

  @Test(dataProvider = "cellValidators",
      dependsOnMethods = "testBuildDependencyTree",
      expectedExceptions = CellGroupValidationEngineException.class,
      expectedExceptionsMessageRegExp = "Cyclic group dependency found: b->c->d->b"
  )
  public void testAssertNoCyclicBad(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    cellValidators.add(new RequireValidator().group("c").matchField("c.2").dependsOn("d"));
    cellValidators.add(new RequireValidator().group("d").matchField("d.1").dependsOn("b"));

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);
    LinkedHashMap<String, LinkedHashSet<String>> dependencyTree = engine.buildDependencyTree(cellValidatorGroups);
    engine.assertNoCyclic(dependencyTree);

  }

  @Test(dataProvider = "cellValidators", dependsOnMethods = "testBuildCellValidatorGroups")
  public void testBuildGroup2Validator2Columns(SheetMeta sheetMeta, List<Dependant> cellValidators) throws Exception {

    CellGroupValidationEngine engine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    LinkedHashMap<String, List<Dependant>> cellValidatorGroups = engine.buildCellValidatorGroups(cellValidators);

    Map<String, Map<Dependant, List<Integer>>> group2Validator2Columns = engine
        .buildGroup2Validator2Columns(cellValidatorGroups);

    assertEquals(group2Validator2Columns.size(), 3);

    for (Map.Entry<Dependant, List<Integer>> entry : group2Validator2Columns.get("a").entrySet()) {
      RequireValidator validator = (RequireValidator) entry.getKey();
      List<Integer> columnIndices = entry.getValue();

      if (validator.getMatchField().equals("a.1")) {
        assertEquals(columnIndices.size(), 1);
        assertTrue(columnIndices.contains(1));
      } else if (validator.getMatchField().equals("a.2")) {
        assertEquals(columnIndices.size(), 1);
        assertTrue(columnIndices.contains(2));
      }
    }

    for (Map.Entry<Dependant, List<Integer>> entry : group2Validator2Columns.get("b").entrySet()) {
      RequireValidator validator = (RequireValidator) entry.getKey();
      List<Integer> columnIndices = entry.getValue();

      if (validator.getMatchField().equals("b.1")) {
        assertEquals(columnIndices.size(), 1);
        assertTrue(columnIndices.contains(3));
      } else if (validator.getMatchField().equals("b.2")) {
        assertEquals(columnIndices.size(), 1);
        assertTrue(columnIndices.contains(4));
      }
    }

    for (Map.Entry<Dependant, List<Integer>> entry : group2Validator2Columns.get("c").entrySet()) {
      Dependant validator = entry.getKey();
      List<Integer> columnIndices = entry.getValue();

      if (validator instanceof RequireValidator) {

        assertEquals(columnIndices.size(), 1);
        assertTrue(columnIndices.contains(5));

      } else if (validator instanceof UnionCellValidator) {

        assertEquals(columnIndices.size(), 2);
        assertTrue(columnIndices.contains(4));
        assertTrue(columnIndices.contains(5));

      }

    }

  }

}
