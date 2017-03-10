package com.supwisdom.spreadsheet.mapper.validation.builder.unioncell;

import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.validation.builder.CellValidatorBatchBuilder;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

/**
 * {@link UnionCellValidator}构建单元
 */
public class UnionCellBuildUnit {

  protected CellValidatorBatchBuilder batchBuilder;

  protected UnionCellValidatorFactory factory;

  protected UnionCellBuildUnitParam unionCellBuildUnitParam = new UnionCellBuildUnitParam();

  public UnionCellBuildUnit(CellValidatorBatchBuilder batchBuilder, UnionCellValidatorFactory factory) {
    this.batchBuilder = batchBuilder;
    this.factory = factory;
  }

  /**
   * @param matchFields 匹配的{@link FieldMeta}
   * @return 自己
   * @see UnionCellValidator#getMatchFields()
   */
  public UnionCellBuildUnit matchFields(String... matchFields) {
    unionCellBuildUnitParam.addMatchFields(matchFields);
    return this;
  }

  /**
   * @param group 分组
   * @return 自己
   * @see UnionCellValidator#getGroup()
   */
  public UnionCellBuildUnit group(String group) {
    unionCellBuildUnitParam.setGroup(group);
    return this;
  }

  /**
   * @param dependsOn 依赖哪些组
   * @return 自己
   * @see UnionCellValidator#getDependsOn()
   */
  public UnionCellBuildUnit dependsOn(String... dependsOn) {
    unionCellBuildUnitParam.addDependsOn(dependsOn);
    return this;
  }

  /**
   * @param errorMessage 错误消息
   * @return 自己
   * @see UnionCellValidator#getErrorMessage()
   */
  public UnionCellBuildUnit errorMessage(String errorMessage) {
    unionCellBuildUnitParam.setErrorMessage(errorMessage);
    return this;
  }

  /**
   * @param additionalParam 构造{@link CellValidator}时需要的额外参数
   * @return 自己
   */
  public UnionCellBuildUnit param(Object additionalParam) {
    unionCellBuildUnitParam.setAdditionalParam(additionalParam);
    return this;
  }

  /**
   * <p>
   * 利用{@link UnionCellValidatorFactory}构建{@link UnionCellValidator}，并添加到{@link CellValidatorBatchBuilder}。
   * </p>
   * 注意：
   * <ol>
   * <li>当有多个matchFields的时候，只会创建一个{@link UnionCellValidator}</li>
   * <li>一定要设定group</li>
   * </ol>
   *
   * @return {@link CellValidatorBatchBuilder}
   */
  public CellValidatorBatchBuilder end() {

    batchBuilder.addValidator(factory.create(unionCellBuildUnitParam));

    return batchBuilder;
  }

}
