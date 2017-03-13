package com.supwisdom.spreadsheet.mapper.bean;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * {@link BeanUtilsBean}的工厂
 * Created by qianjia on 2017/3/13.
 */
public interface BeanUtilsBeanFactory {

  /**
   * 获得{@link BeanUtilsBean}，实现类应该采用单例模式
   *
   * @return
   */
  BeanUtilsBean getInstance();

}
