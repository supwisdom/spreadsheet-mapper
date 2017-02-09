package com.supwisdom.spreadsheet.mapper.w2o.validation.rule;

import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.DependencyValidator;
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
public class DefaultDependencyValidatorBuilder implements DependencyValidatorBuilder {

  private List<DependencyValidator> validators = new ArrayList<>();

  @Override
  public RuleBuilder single(SingleCellValidatorFactory factory) {
    return new SingleCellRuleBuilder(factory);
  }

  @Override
  public RuleBuilder multi(MultiCellValidatorFactory factory) {
    return new MultiCellRuleBuilder(factory);
  }

  @Override
  public List<DependencyValidator> build() {
    return validators;
  }

  public abstract class CellRuleBuilderTemplate<T extends DependencyValidatorFactory> implements RuleBuilder {

    protected T factory;

    protected List<String> matchFields = new ArrayList<>();

    protected DependencyRuleParam param = new DependencyRuleParam();

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
    public DependencyValidatorBuilder end() {

      MultiCellValidator validator = factory.create(param, matchFields);
      DefaultDependencyValidatorBuilder.this.validators.add(validator);

      return DefaultDependencyValidatorBuilder.this;
    }
  }

  public class SingleCellRuleBuilder extends CellRuleBuilderTemplate<SingleCellValidatorFactory> {

    protected SingleCellRuleBuilder(SingleCellValidatorFactory factory) {
      super(factory);
    }

    @Override
    public DependencyValidatorBuilder end() {

      boolean groupNull = StringUtils.isBlank(param.getGroup());

      for (String field : matchFields) {

        if (groupNull) {
          param.setGroup(field);
        }

        SingleCellValidator validator = factory.create(param, field);
        DefaultDependencyValidatorBuilder.this.validators.add(validator);
      }

      return DefaultDependencyValidatorBuilder.this;
    }

  }

}
