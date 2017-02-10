package com.supwisdom.spreadsheet.mapper.validation.engine;

import com.supwisdom.spreadsheet.mapper.validation.validator.cell.Validator;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Created by hanwen on 2017/1/6.
 */
public class ValidatorDependencyGraphHelper {

  private ValidatorDependencyGraphHelper() {
    // default constructor
  }

  /**
   * create directed graph of dependency validators
   *
   * @param validatorMap dependency validators
   * @return the directed graph
   */
  public static LinkedHashMap<String, LinkedHashSet<String>> buildVGraph(Map<String, List<Validator>> validatorMap) {
    LinkedHashMap<String, LinkedHashSet<String>> vGraph = new LinkedHashMap<>();

    for (Map.Entry<String, List<Validator>> entry : validatorMap.entrySet()) {
      String key = entry.getKey();
      vGraph.put(key, new LinkedHashSet<String>());

      for (Validator dataValidator : entry.getValue()) {

        Set<String> dependsOn = dataValidator.getDependsOn();
        if (CollectionUtils.isNotEmpty(dependsOn)) {

          vGraph.get(key).addAll(dependsOn);
        }
      }
    }

    return vGraph;
  }
}
