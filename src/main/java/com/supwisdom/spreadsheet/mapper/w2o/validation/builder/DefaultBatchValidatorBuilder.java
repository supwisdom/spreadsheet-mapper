package com.supwisdom.spreadsheet.mapper.w2o.validation.builder;

import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.Validator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.MultiCellValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.SingleCellValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hanwen on 2017/1/20.
 */
public class DefaultBatchValidatorBuilder implements BatchValidatorBuilder {

  private List<Validator> validators = new ArrayList<>();

  @Override
  public RuleBuilder single(SingleCellValidatorFactory factory) {
    return new SingleCellRuleBuilder(factory);
  }

  @Override
  public RuleBuilder multi(MultiCellValidatorFactory factory) {
    return new MultiCellRuleBuilder(factory);
  }

  @Override
  public List<Validator> build() {
    return validators;
  }

  public abstract class CellRuleBuilderTemplate<T extends ValidatorFactory> implements RuleBuilder {

    protected T factory;

    protected List<String> matchFields = new ArrayList<>();

    protected ValidatorFactoryParam param = new ValidatorFactoryParam();

    protected CellRuleBuilderTemplate(T factory) {
      this.factory = factory;
    }

    @Override
    public RuleBuilder matchFields(String... matchFields) {
      if (matchFields == null) {
        return this;
      }
      Collections.addAll(this.matchFields, matchFields);
      return this;
    }

    @Override
    public RuleBuilder group(String group) {
      param.setGroup(group);
      return this;
    }

    @Override
    public RuleBuilder dependsOn(String... dependsOn) {
      if (dependsOn == null) {
        return this;
      }
      param.setDependsOn(Arrays.asList(dependsOn));
      return this;
    }

    @Override
    public RuleBuilder errorMessage(String errorMessage) {
      param.setErrorMessage(errorMessage);
      return this;
    }

    @Override
    public RuleBuilder param(Object additionalParam) {
      param.setAdditionalParam(additionalParam);
      return this;
    }


  }

  public class MultiCellRuleBuilder extends CellRuleBuilderTemplate<MultiCellValidatorFactory> {

    protected MultiCellRuleBuilder(MultiCellValidatorFactory factory) {
      super(factory);
    }

    @Override
    public BatchValidatorBuilder end() {

      MultiCellValidator validator = factory.create(param, matchFields);
      DefaultBatchValidatorBuilder.this.validators.add(validator);

      return DefaultBatchValidatorBuilder.this;
    }
  }

  public class SingleCellRuleBuilder extends CellRuleBuilderTemplate<SingleCellValidatorFactory> {

    protected SingleCellRuleBuilder(SingleCellValidatorFactory factory) {
      super(factory);
    }

    @Override
    public BatchValidatorBuilder end() {

      boolean groupNull = StringUtils.isBlank(param.getGroup());

      for (String field : matchFields) {

        if (groupNull) {
          param.setGroup(field);
        }

        SingleCellValidator validator = factory.create(param, field);
        DefaultBatchValidatorBuilder.this.validators.add(validator);
      }

      return DefaultBatchValidatorBuilder.this;
    }

  }

}
