package com.supwisdom.spreadsheet.mapper.validation.engine;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by qianjia on 2017/2/11.
 */
@Test(groups = "graphCyclicCheckerTest")
public class GraphCyclicCheckerTest {

  @DataProvider
  public Object[][] dependencyTree() {

    return new Object[][] {
        new Object[] {
            build(
                new Node("n1", new String[] { "n1" })
            ),
            true, "n1,n1"
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2" }),
                new Node("n2", new String[] { "n1" })
            ),
            true, "n1,n2,n1"
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n1", "n2" }),
                new Node("n2", new String[] { "n1" })
            ),
            true, "n1,n1"
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2" }),
                new Node("n2", new String[] { "n4", "n3" }),
                new Node("n3", new String[] { "n1" }),
                new Node("n4", new String[] { "n5", "n6" }),
                new Node("n5", null),
                new Node("n6", null)
            ),
            true, "n1,n2,n3,n1"
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2" }),
                new Node("n2", new String[] { "n4", "n3" }),
                new Node("n3", new String[] { "n7" }),
                new Node("n4", new String[] { "n5", "n6" }),
                new Node("n5", null),
                new Node("n6", new String[] { "n7" }),
                new Node("n7", null)
            ),
            false, ""
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "x1" }),
                new Node("n2", new String[] { "x1", "x2" }),
                new Node("x1", null),
                new Node("x2", null)
            ),
            false, ""
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "x1" }),
                new Node("x1", new String[] { "x1" })
            ),
            true, "x1,x1"
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2", "n3", "n7" }),
                new Node("n2", new String[] { "n4" }),
                new Node("n3", new String[] { "n6" }),
                new Node("n4", new String[] { "n5", "n6" }),
                new Node("n5", new String[] { "n7" }),
                new Node("n6", new String[] { "n7" }),
                new Node("n7", null)
            ),
            false, ""
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2" }),
                new Node("n2", new String[] { "n4" }),
                new Node("n3", new String[] { "n4", "n5" }),
                new Node("n4", new String[] { "n6", "n1" }),
                new Node("n5", new String[] { "n6" }),
                new Node("n6", null)
            ),
            true, "n1,n2,n4,n1"
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2" }),
                new Node("n2", new String[] { "n3", "n7" }),
                new Node("n3", new String[] { "n4", "n5" }),
                new Node("n4", new String[] { "n8" }),
                new Node("n5", new String[] { "n6" }),
                new Node("n6", new String[] { "n8" }),
                new Node("n7", new String[] { "n5", "n9", "n11" }),
                new Node("n8", new String[] { "n9" }),
                new Node("n9", new String[] { "n10", "n11" }),
                new Node("n10", new String[] { "n12" }),
                new Node("n11", new String[] { "n10" }),
                new Node("n12", null)
            ),
            false, ""
        },
        new Object[] {
            build(
                new Node("n1", new String[] { "n2" }),
                new Node("n2", new String[] { "n3", "n7" }),
                new Node("n3", new String[] { "n4", "n5" }),
                new Node("n4", new String[] { "n8" }),
                new Node("n5", new String[] { "n6" }),
                new Node("n6", new String[] { "n8" }),
                new Node("n7", new String[] { "n5", "n9", "n11" }),
                new Node("n8", new String[] { "n9" }),
                new Node("n9", new String[] { "n10", "n11" }),
                new Node("n10", new String[] { "n12" }),
                new Node("n11", new String[] { "n10", "n6" }),
                new Node("n12", null)
            ),
            true, "n8,n9,n11,n6,n8"
        }

    };
  }

  private LinkedHashMap<String, LinkedHashSet<String>> build(Node... nodes) {

    LinkedHashMap<String, LinkedHashSet<String>> vGraph = new LinkedHashMap<>();
    for (Node node : nodes) {
      LinkedHashSet<String> dependsOn = new LinkedHashSet<>();
      dependsOn.addAll(node.getDepends());
      vGraph.put(node.getName(), dependsOn);
    }

    return vGraph;

  }

  @Test(dataProvider = "dependencyTree")
  public void testCycling(
      LinkedHashMap<String, LinkedHashSet<String>> vGraph,
      Boolean expectedCycling,
      String expectedCycleString) throws Exception {

    GraphCyclicChecker checker = new GraphCyclicChecker(vGraph);

    assertEquals(Boolean.valueOf(checker.cycling()), expectedCycling);
    assertEquals(StringUtils.join(checker.getCycle(), ','), expectedCycleString);

  }

  private static class Node {

    private final String name;
    private final LinkedHashSet<String> depends = new LinkedHashSet<>();

    public Node(String name, String[] dependNodes) {
      this.name = name;
      if (dependNodes != null) {
        this.depends.addAll(Arrays.asList(dependNodes));
      }
    }

    public String getName() {
      return name;
    }

    public LinkedHashSet<String> getDepends() {
      return depends;
    }

  }

}

