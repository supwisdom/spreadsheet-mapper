package com.supwisdom.spreadsheet.mapper.validation.engine;

import org.apache.commons.lang3.StringUtils;

import javax.xml.ws.soap.Addressing;
import java.util.*;

/**
 * <pre>
 * use Tarjan's strongly connected components algorithm to check cycling,
 * in directed graph, if a strongly connected component not a isolated node, means has cycle.
 * </pre>
 * Created by hanwen on 2017/1/5.
 */
public class GraphCyclicChecker {

  private LinkedHashMap<String, LinkedHashSet<String>> vGraph = new LinkedHashMap<>();
  private Stack<String> vStack = new Stack<>();
  private Map<String, Integer> vIndex = new HashMap<>();
  private Map<String, Integer> vLowLink = new HashMap<>();
  private int index;

  // found exists cycle
  private final List<String> cycle = new ArrayList<>();

  public GraphCyclicChecker(LinkedHashMap<String, LinkedHashSet<String>> vGraph) {
    this.vGraph = vGraph;
    for (String s : vGraph.keySet()) {
      vIndex.put(s, 0);
      vLowLink.put(s, 0);
    }
  }

  public boolean cycling() {

    this.cycle.clear();

    for (String v : vGraph.keySet()) {

      if (vGraph.get(v).contains(v)) {
        cycle.add(v);
        cycle.add(v);
      }

      if (!cycle.isEmpty()) {
        return true;
      }

      if (vIndex.get(v) == 0) {
        strongConnect(v);
      }
    }

    return false;
  }

  public List<String> getCycle() {
    return cycle;
  }

  private void strongConnect(String v) {
    index++;
    vIndex.put(v, index);
    vLowLink.put(v, index);
    vStack.push(v);

    for (String w : vGraph.get(v)) {

      if (!vIndex.containsKey(w)) {
        throw new RuntimeException("Broken link");
      } else if (vIndex.get(w) == 0) {
        strongConnect(w);
        vLowLink.put(v, Math.min(vLowLink.get(v), vLowLink.get(w)));
      } else if (vStack.contains(w)) {
        vLowLink.put(v, Math.min(vLowLink.get(v), vIndex.get(w)));
      }
    }

    if (!cycle.isEmpty()) {
      return;
    }

    if (Objects.equals(vLowLink.get(v), vIndex.get(v))) {
      populateConnectedComponents(v);
    }
  }

  private void populateConnectedComponents(String v) {

    List<String> connectedComponents = new ArrayList<>();
    String connectedComponent = null;

    while (!StringUtils.equals(connectedComponent, v)) {
      connectedComponent = vStack.pop();
      connectedComponents.add(0, connectedComponent);
    }

    if (connectedComponents.size() > 1) {
      cycle.addAll(connectedComponents);
      cycle.add(cycle.get(0));
    }

  }

}
