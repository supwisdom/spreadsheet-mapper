package com.supwisdom.spreadsheet.mapper.validation.builder.cell;

import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.builder.CellValidatorBatchBuilder;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * {@link CellValidator}构建单元
 */
public class CellBuildUnit {

  protected CellValidatorBatchBuilder cellValidatorBatchBuilder;

  protected CellValidatorFactory factory;

  protected List<String> matchFields = new ArrayList<>();

  protected String group;

  protected List<String> dependsOn = new ArrayList<>(2);

  protected String errorMessage;

  protected Object additionalParam;

  public CellBuildUnit(CellValidatorBatchBuilder cellValidatorBatchBuilder, CellValidatorFactory factory) {
    this.cellValidatorBatchBuilder = cellValidatorBatchBuilder;
    this.factory = factory;
  }

  /**
   * @param matchFields 匹配的{@link FieldMeta}
   * @return 自己
   * @see CellValidator#getMatchField()
   */
  public CellBuildUnit matchFields(String... matchFields) {
    if (matchFields == null) {
      return this;
    }
    Collections.addAll(this.matchFields, matchFields);
    return this;
  }

  /**
   * @param group 分组
   * @return 自己
   * @see CellValidator#getGroup()
   */
  public CellBuildUnit group(String group) {
    this.group = group;
    return this;
  }

  /**
   * @param dependsOn 依赖哪些组
   * @return 自己
   * @see CellValidator#getDependsOn()
   */
  public CellBuildUnit dependsOn(String... dependsOn) {
    if (dependsOn == null) {
      return this;
    }
    this.dependsOn.addAll(Arrays.asList(dependsOn));
    return this;
  }

  /**
   * @param errorMessage 错误消息
   * @return 自己
   * @see CellValidator#getErrorMessage()
   */
  public CellBuildUnit errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * @param additionalParam 构造{@link CellValidator}时需要的额外参数
   * @return 自己
   */
  public CellBuildUnit param(Object additionalParam) {
    this.additionalParam = additionalParam;
    return this;
  }

  /**
   * <p>
   * 利用{@link CellValidatorFactory}构建{@link CellValidator}，并添加到{@link CellValidatorBatchBuilder}。
   * </p>
   * 注意：
   * <ol>
   * <li>当有多个matchFields的时候，就会创建多个{@link CellValidator}</li>
   * <li>当group为blank的时候，会用matchField替代</li>
   * </ol>
   *
   * @return {@link CellValidatorBatchBuilder}
   */
  public CellValidatorBatchBuilder end() {

    for (CellBuildUnitParam cellBuildUnitParam : createBuildUnitParams()) {
      cellValidatorBatchBuilder.addValidator(factory.create(cellBuildUnitParam));
    }
    return cellValidatorBatchBuilder;
  }

  private List<CellBuildUnitParam> createBuildUnitParams() {

    List<CellBuildUnitParam> params = new ArrayList<>(this.matchFields.size());
    for (String matchField : matchFields) {

      CellBuildUnitParam newParam = new CellBuildUnitParam();

      if (StringUtils.isBlank(this.group)) {
        newParam.setGroup(matchField);
      } else {
        newParam.setGroup(this.group);
      }

      newParam.setMatchField(matchField);
      newParam.getDependsOn().addAll(this.dependsOn);
      newParam.setErrorMessage(matchField);
      newParam.setAdditionalParam(this.additionalParam);
      newParam.setErrorMessage(this.errorMessage);

      params.add(newParam);
    }

    return params;

  }
}
