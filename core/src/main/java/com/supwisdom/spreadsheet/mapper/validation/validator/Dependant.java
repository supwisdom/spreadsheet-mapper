package com.supwisdom.spreadsheet.mapper.validation.validator;

import java.util.LinkedHashSet;

/**
 * 依赖者接口，提供我自己是属于哪个group和我依赖哪些group的数据
 * Created by qianjia on 2017/2/11.
 */
public interface Dependant {

  /**
   * @return 我自己属于哪个group
   */
  String getGroup();

  /**
   * @return 我依赖哪些group
   */
  LinkedHashSet<String> getDependsOn();

}
