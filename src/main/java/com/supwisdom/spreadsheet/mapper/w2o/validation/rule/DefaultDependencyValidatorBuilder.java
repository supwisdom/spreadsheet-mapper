package com.supwisdom.spreadsheet.mapper.w2o.validation.rule;

import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.DependencyValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.SingleCellValidator;
import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.MultiCellValidator;

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
  public RuleBuilder single(String name) {
    return new SingleCellRuleBuilder(name);
  }

  @Override
  public RuleBuilder multi(String name) {
    return new MultiCellRuleBuilder(name);
  }

  @Override
  public List<DependencyValidator> build() {
    return validators;
  }

  public class MultiCellRuleBuilder implements RuleBuilder {

    private String name;

    private List<String> matchFields = new ArrayList<>();

    private DependencyRuleParam param = new DependencyRuleParam();

    MultiCellRuleBuilder(String name) {
      this.name = name;
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

    @Override
    public DependencyValidatorBuilder end() {

      DependencyValidatorFactory factory = DependencyValidatorFactoryRegisterer.GLOBAL.getFactoryInstance(name);

      if (!(factory instanceof MultiCellValidatorFactory)) {
        throw new IllegalArgumentException("rule[" + name + "] not a multi cell validator rule");
      }

      MultiCellValidatorFactory multiCellValidatorFactory = (MultiCellValidatorFactory) factory;
      MultiCellValidator validator = multiCellValidatorFactory.create(param, matchFields);
      DefaultDependencyValidatorBuilder.this.validators.add(validator);

      return DefaultDependencyValidatorBuilder.this;
    }
  }

  public class SingleCellRuleBuilder implements RuleBuilder {

    private String name;

    private List<String> matchFields = new ArrayList<>();

    private DependencyRuleParam param = new DependencyRuleParam();

    SingleCellRuleBuilder(String name) {
      this.name = name;
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

    @Override
    public DependencyValidatorBuilder end() {

      DependencyValidatorFactory factory = DependencyValidatorFactoryRegisterer.GLOBAL.getFactoryInstance(name);

      if (!(factory instanceof SingleCellValidatorFactory)) {
        throw new IllegalArgumentException("rule[" + name + "] not a single cell validator rule");
      }

      SingleCellValidatorFactory singleCellValidatorFactory = (SingleCellValidatorFactory) factory;

      boolean groupNull = StringUtils.isBlank(param.getGroup());

      for (String field : matchFields) {

        if (groupNull) {
          param.setGroup(field);
        }

        SingleCellValidator validator = singleCellValidatorFactory.create(param, field);
        DefaultDependencyValidatorBuilder.this.validators.add(validator);
      }

      return DefaultDependencyValidatorBuilder.this;
    }

  }

}
