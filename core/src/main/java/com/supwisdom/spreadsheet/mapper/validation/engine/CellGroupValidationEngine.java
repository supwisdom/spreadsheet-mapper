package com.supwisdom.spreadsheet.mapper.validation.engine;

import com.supwisdom.spreadsheet.mapper.model.core.Cell;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * use simply validateGroup to do dependency validate, depends on dependencies no cycle {@link GraphCyclicChecker}
 * Created by hanwen on 2017/1/6.
 */
public class CellGroupValidationEngine {

  private static final Logger LOGGER = LoggerFactory.getLogger(CellGroupValidationEngine.class);

  private final List<Dependant> cellValidators;

  private final SheetMeta sheetMeta;

  // group -> validators
  private transient LinkedHashMap<String, List<Dependant>> cellValidatorGroups = new LinkedHashMap<>();

  // group -> dependsOn
  private transient LinkedHashMap<String, LinkedHashSet<String>> dependencyTree = new LinkedHashMap<>();

  // group -> validator -> columns
  private transient Map<String, Map<Dependant, List<Integer>>> group2Validator2Columns = new HashMap<>();

  private transient Map<String, Boolean> result = new HashMap<>();

  private transient List<Message> errorMessages = new ArrayList<>();

  public CellGroupValidationEngine(SheetMeta sheetMeta, List<Dependant> cellValidators) {

    if (cellValidators != null) {
      this.cellValidators = new ArrayList<>(cellValidators);
    } else {
      this.cellValidators = Collections.emptyList();
    }
    this.sheetMeta = sheetMeta;

  }

  /**
   * 初始化工作，主要是计算哪些field对应哪些CellValidator/UnionCellValidator
   */
  public void initialize() {

    this.cellValidatorGroups = buildCellValidatorGroups(cellValidators);
    this.dependencyTree = buildDependencyTree(cellValidatorGroups);
    assertNoMissingGroup(dependencyTree);
    assertNoCyclic(dependencyTree);
    this.group2Validator2Columns = buildGroup2Validator2Columns(cellValidatorGroups);

  }

  /**
   * 构建 CellValidator 分组
   *
   * @param cellValidators
   * @return
   */
  protected LinkedHashMap<String, List<Dependant>> buildCellValidatorGroups(List<Dependant> cellValidators) {

    LinkedHashMap<String, List<Dependant>> group2Validators = new LinkedHashMap<>();

    for (Dependant cellValidator : cellValidators) {

      String group = cellValidator.getGroup();
      if (!group2Validators.containsKey(group)) {
        group2Validators.put(group, new ArrayList<Dependant>());
      }
      group2Validators.get(group).add(cellValidator);

    }

    return group2Validators;
  }

  /**
   * 构建分组依赖树
   *
   * @param group2Validators
   * @return
   */
  protected LinkedHashMap<String, LinkedHashSet<String>> buildDependencyTree(
      LinkedHashMap<String, List<Dependant>> group2Validators) {

    LinkedHashMap<String, LinkedHashSet<String>> groupDependencies = new LinkedHashMap<>();

    for (Map.Entry<String, List<Dependant>> entry : group2Validators.entrySet()) {
      String key = entry.getKey();
      groupDependencies.put(key, new LinkedHashSet<String>());

      for (Dependant dependant : entry.getValue()) {

        Set<String> dependsOn = dependant.getDependsOn();
        if (CollectionUtils.isNotEmpty(dependsOn)) {

          groupDependencies.get(key).addAll(dependsOn);
        }
      }
    }

    return groupDependencies;

  }

  /**
   * 断言不存在丢失的group
   *
   * @param dependencyTree
   */
  protected void assertNoMissingGroup(LinkedHashMap<String, LinkedHashSet<String>> dependencyTree) {

    Set<String> allGroups = dependencyTree.keySet();

    for (Map.Entry<String, LinkedHashSet<String>> entry : dependencyTree.entrySet()) {
      String group = entry.getKey();
      Set<String> dependsOn = entry.getValue();

      Collection missingGroups = CollectionUtils.subtract(dependsOn, allGroups);
      if (!missingGroups.isEmpty()) {

        throw new CellGroupValidationEngineException(
            "Group[" + group + "] depends on missing group(s)[" + StringUtils.join(missingGroups, ',') + "]");
      }
    }

  }

  /**
   * 断言不存在循环依赖
   *
   * @param dependencyTree
   */
  protected void assertNoCyclic(LinkedHashMap<String, LinkedHashSet<String>> dependencyTree) {

    GraphCyclicChecker graphCyclicChecker = new GraphCyclicChecker(dependencyTree);
    if (graphCyclicChecker.cycling()) {
      throw new CellGroupValidationEngineException(
          "Cyclic group dependency found: " + StringUtils.join(graphCyclicChecker.getCycle(), "->"));
    }

  }

  protected Map<String, Map<Dependant, List<Integer>>> buildGroup2Validator2Columns(
      LinkedHashMap<String, List<Dependant>> cellValidatorGroups) {

    Map<String, Map<Dependant, List<Integer>>> group2validator2columns = new HashMap<>();

    for (Map.Entry<String, List<Dependant>> entry : cellValidatorGroups.entrySet()) {

      String group = entry.getKey();
      List<Dependant> validators = entry.getValue();

      Map<Dependant, List<Integer>> validator2columns = new HashMap<>();
      group2validator2columns.put(group, validator2columns);

      for (Dependant validator : validators) {

        List<Integer> columnIndices = new ArrayList<>(0);

        if (CellValidator.class.isAssignableFrom(validator.getClass())) {

          Integer columnIndex = getValidateColumnIndex(sheetMeta, (CellValidator) validator);
          if (columnIndex != null) {
            columnIndices.add(columnIndex);
          }

        } else if (UnionCellValidator.class.isAssignableFrom(validator.getClass())) {

          LinkedHashSet<Integer> indices = getValidateColumnIndices(sheetMeta, (UnionCellValidator) validator);
          if (indices != null) {
            columnIndices.addAll(indices);
          }

        }

        validator2columns.put(validator, columnIndices);

      }

    }

    return group2validator2columns;
  }

  private Integer getValidateColumnIndex(SheetMeta sheetMeta, CellValidator validator) {

    String matchField = validator.getMatchField();
    FieldMeta fieldMeta = sheetMeta.getFieldMeta(matchField);
    if (fieldMeta == null) {
      LOGGER.debug("Couldn't find field [{}] for CellValidator [{}]", matchField, validator.getClass().getName());
      return null;
    }
    return fieldMeta.getColumnIndex();

  }

  private LinkedHashSet<Integer> getValidateColumnIndices(SheetMeta sheetMeta, UnionCellValidator validator) {

    LinkedHashSet<Integer> columnIndices = new LinkedHashSet<>(validator.getMatchFields().size());
    for (String matchField : validator.getMatchFields()) {
      FieldMeta fieldMeta = sheetMeta.getFieldMeta(matchField);
      if (fieldMeta == null) {
        LOGGER
            .debug("Couldn't find field [{}] for UnionCellValidator [{}]", matchField, validator.getClass().getName());
        return null;
      }
      columnIndices.add(fieldMeta.getColumnIndex());
    }
    return columnIndices;

  }

  public boolean validate(Row row, SheetMeta sheetMeta) {

    for (String group : dependencyTree.keySet()) {
      if (!result.containsKey(group)) {
        validateGroup(row, sheetMeta, group);
      }
    }

    return !result.values().contains(Boolean.FALSE);
  }

  public List<Message> getErrorMessages() {
    return errorMessages;
  }

  private void validateGroup(Row row, SheetMeta sheetMeta, String group) {

    LinkedHashSet<String> upstreamGroups = dependencyTree.get(group);

    // 上游group没有校验过的话，先校验上游group
    for (String upstreamGroup : upstreamGroups) {
      if (!result.containsKey(upstreamGroup)) {
        validateGroup(row, sheetMeta, upstreamGroup);
      }
    }

    // 判断上游group是否通过，如果没有通过则跳过
    for (String upstreamGroup : upstreamGroups) {
      if (result.get(upstreamGroup) == null) {
        LOGGER.debug("Skip validation group [{}] because upstream group [{}] skip", group, upstreamGroup);
        result.put(group, null);
        return;
      }
      if (!result.get(upstreamGroup)) {
        LOGGER.debug("Skip validation group [{}] because upstream group [{}] failure", group, upstreamGroup);
        result.put(group, null);
        return;
      }
    }

    // 执行本group校验
    List<Dependant> dependants = cellValidatorGroups.get(group);

    LOGGER.debug("Start validation group [{}] {} validator(s).", group, dependants.size());

    boolean groupSucceeded = true;
    for (Dependant dependant : dependants) {

      List<Integer> columnIndices = group2Validator2Columns.get(group).get(dependant);

      // group内的validator按顺序执行，只要有一个失败了，那么后面的就不会再执行了
      groupSucceeded = groupSucceeded && validateCell(row, sheetMeta, dependant, columnIndices);
      if (!groupSucceeded) {
        LOGGER.debug("validation group [{}] validation failed on [{}]", group, dependant.getClass().getName());
        break;
      }
    }
    result.put(group, groupSucceeded);

  }

  private boolean validateCell(Row row, SheetMeta sheetMeta, Dependant dependant, List<Integer> columnIndices) {

    if (UnionCellValidator.class.isAssignableFrom(dependant.getClass())) {

      return executeValidator((UnionCellValidator) dependant, sheetMeta, row, columnIndices);

    } else if (CellValidator.class.isAssignableFrom(dependant.getClass())) {

      return executeValidator(columnIndices, sheetMeta, row, (CellValidator) dependant);

    }

    LOGGER.debug("Unsupported Validator [{}]", dependant.getClass().getName());
    return false;
  }

  private boolean executeValidator(UnionCellValidator validator, SheetMeta sheetMeta, Row row,
      List<Integer> columnIndices) {

    if (CollectionUtils.isEmpty(columnIndices)) {
      LOGGER.debug("Skip UnionCellValidator [{}]", validator.getClass().getName());
      return true;
    }

    List<Cell> cells = new ArrayList<>();
    List<FieldMeta> fieldMetas = new ArrayList<>();

    for (Integer columnIndex : columnIndices) {
      cells.add(row.getCell(columnIndex));
      fieldMetas.add(sheetMeta.getFieldMeta(columnIndex));
    }

    boolean result = validator.validate(cells, fieldMetas);

    if (!result) {

      String errorMessage = validator.getErrorMessage();

      if (StringUtils.isNotBlank(errorMessage)) {

        for (FieldMeta fieldMeta : fieldMetas) {
          errorMessages.add(
              new MessageBean(MessageWriteStrategies.COMMENT, errorMessage, row.getSheet().getIndex(), row.getIndex(),
                  fieldMeta.getColumnIndex()));
        }
      }

    }

    return result;

  }

  private boolean executeValidator(List<Integer> columnIndices, SheetMeta sheetMeta, Row row,
      CellValidator validator) {

    if (CollectionUtils.isEmpty(columnIndices)) {
      LOGGER.debug("Skip CellValidator [{}]", validator.getClass().getName());
      return true;
    }

    int columnIndex = columnIndices.get(0);

    Cell cell = row.getCell(columnIndex);
    FieldMeta fieldMeta = sheetMeta.getFieldMeta(columnIndex);

    boolean result = validator.validate(cell, fieldMeta);

    if (!result) {

      String errorMessage = validator.getErrorMessage();

      if (StringUtils.isNotBlank(errorMessage)) {
        errorMessages.add(
            new MessageBean(MessageWriteStrategies.COMMENT, errorMessage,
                row.getSheet().getIndex(), row.getIndex(), fieldMeta.getColumnIndex()));
      }

    }

    return result;
  }

}
