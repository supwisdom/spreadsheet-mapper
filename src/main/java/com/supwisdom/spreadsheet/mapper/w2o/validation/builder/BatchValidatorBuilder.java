package com.supwisdom.spreadsheet.mapper.w2o.validation.builder;

import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.Validator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.MultiCellValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.SingleCellValidator;

import java.util.List;

/**
 * dependency validator builder
 * <p>
 * Created by hanwen on 2017/1/23.
 */
public interface BatchValidatorBuilder {

  /**
   * start build a single cell validate rule
   *
   * @param factory {@link ValidatorFactory}
   * @return {@link RuleBuilder}
   * @see SingleCellValidatorFactory
   * @see SingleCellValidator
   */
  RuleBuilder single(SingleCellValidatorFactory factory);

  /**
   * start build a multi cell validate rule
   *
   * @param factory {@link ValidatorFactory}
   * @return {@link RuleBuilder}
   * @see MultiCellValidatorFactory
   * @see MultiCellValidator
   */
  RuleBuilder multi(MultiCellValidatorFactory factory);

  /**
   * build validators from supplied rules
   *
   * @return {@link SingleCellValidator}
   */
  List<Validator> build();

  /**
   * validate rule builder
   */
  interface RuleBuilder {

    /**
     * @param matchFields {@link SingleCellValidator#getMatchField()}
     * @return {@link RuleBuilder}
     */
    RuleBuilder matchFields(String... matchFields);

    /**
     * if empty default is field
     *
     * @param group {@link SingleCellValidator#getGroup()}
     * @return {@link RuleBuilder}
     */
    RuleBuilder group(String group);

    /**
     * @param dependsOn {@link SingleCellValidator#getDependsOn()}
     * @return {@link RuleBuilder}
     */
    RuleBuilder dependsOn(String... dependsOn);

    /**
     * @param errorMessage {@link SingleCellValidator#getErrorMessage()}
     * @return {@link RuleBuilder}
     */
    RuleBuilder errorMessage(String errorMessage);

    /**
     * @param additionalParam the additional param validator need
     * @return {@link RuleBuilder}
     */
    RuleBuilder param(Object additionalParam);

    /**
     * finish a rule
     *
     * @return {@link BatchValidatorBuilder}
     */
    BatchValidatorBuilder end();
  }
}
