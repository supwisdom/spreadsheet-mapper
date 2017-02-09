package com.supwisdom.spreadsheet.mapper.w2o.validation.rule;

import com.supwisdom.spreadsheet.mapper.w2o.validation.WorkbookValidateException;
import com.supwisdom.spreadsheet.mapper.w2o.validation.rule.buildin.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * dependency validator factory registerer
 * <p>
 * Created by hanwen on 2017/1/20.
 */
public class DependencyValidatorFactoryRegisterer {

  /**
   * some build-in validators name
   */
  public static final String RULE_BOOL = "bool";
  public static final String RULE_NUMBER = "number";
  public static final String RULE_DIGITS = "digits";
  public static final String RULE_LOCAL_DATE = "localDate";
  public static final String RULE_LOCAL_DATE_TIME = "localDateTime";
  public static final String RULE_REGEX = "regex";
  public static final String RULE_REQUIRE = "require";
  public static final String RULE_NUMBER_SCALE_RANGE = "numberScaleRange";
  public static final String RULE_UNIQUE = "unique";
  public static final String RULE_MULTI_UNIQUE = "multiUnique";

  private static final Logger LOGGER = LoggerFactory.getLogger(DependencyValidatorFactoryRegisterer.class);

  public static DependencyValidatorFactoryRegisterer GLOBAL = new DependencyValidatorFactoryRegisterer();

  private ConcurrentHashMap<String, Class<? extends DependencyValidatorFactory>> validatorFactories = new ConcurrentHashMap<>();

  {
    registerBuildInValidatorFactory();
  }

  /**
   * <pre>
   * register validator factory clazz with name, the name will process by {@link String#toLowerCase()},
   * the name is uppercase and lowercase insensitive when register
   * </pre>
   *
   * @param name         register name
   * @param factoryClazz factory clazz
   */
  public void registerFactory(String name, Class<? extends DependencyValidatorFactory> factoryClazz) {
    if (name == null) {
      throw new IllegalArgumentException("register factory name can not be null");
    }
    String registerName = buildRegisterName(name);
    if (validatorFactories.containsKey(registerName)) {
      throw new IllegalArgumentException("can not register duplicate rule name, already registered rule name [" + registerName + "]");
    }

    validatorFactories.put(registerName, factoryClazz);
    LOGGER.info("registered validator factory[" + factoryClazz.getName() + "] success, named[" + name + "]");
  }

  /**
   * get validator factory clazz
   *
   * @param name register name
   * @return validator factory clazz
   */
  public Class<? extends DependencyValidatorFactory> getFactoryClazz(String name) {
    if (name == null) {
      throw new IllegalArgumentException("register factory name can not be null");
    }

    Class<? extends DependencyValidatorFactory> factoryClazz = validatorFactories.get(buildRegisterName(name));

    if (factoryClazz == null) {
      throw new IllegalArgumentException("not factory registered as [" + buildRegisterName(name) + "]");
    }

    return factoryClazz;
  }

  /**
   * get validator factory instance by register name
   *
   * @param name register name
   * @return validator factory
   */
  public DependencyValidatorFactory getFactoryInstance(String name) {

    Class<? extends DependencyValidatorFactory> factoryClazz = getFactoryClazz(name);

    try {
      return factoryClazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      throw new WorkbookValidateException("instantiation factory[" + factoryClazz.getName() + "] failure, factory need default constructor");
    }
  }

  private void registerBuildInValidatorFactory() {
    registerFactory(RULE_BOOL, BooleanValidatorFactory.class);
    registerFactory(RULE_NUMBER, NumberValidatorFactory.class);
    registerFactory(RULE_DIGITS, DigitsValidatorFactory.class);
    registerFactory(RULE_LOCAL_DATE, LocalDateValidatorFactory.class);
    registerFactory(RULE_LOCAL_DATE_TIME, LocalDateTimeValidatorFactory.class);
    registerFactory(RULE_REGEX, RegexFormatValidatorFactory.class);
    registerFactory(RULE_REQUIRE, RequireValidatorFactory.class);
    registerFactory(RULE_NUMBER_SCALE_RANGE, NumberScaleRangeValidatorFactory.class);
    registerFactory(RULE_UNIQUE, UniqueValidatorFactory.class);

    registerFactory(RULE_MULTI_UNIQUE, MultiUniqueValidatorFactory.class);
  }

  private String buildRegisterName(String name) {
    return name.toLowerCase();
  }
}
