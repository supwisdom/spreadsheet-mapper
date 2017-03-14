package com.supwisdom.spreadsheet.mapper.bean;

import jodd.bean.BeanUtil;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qianjia on 2017/3/14.
 */
public class BeanHelperBean implements BeanHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelperBean.class);

  @Override
  public Object getProperty(Object object, String propertyPath) throws BeanPropertyReadException {
    try {
      return PropertyUtils.getProperty(object, propertyPath);
    } catch (NestedNullException e) {
      LOGGER.debug("Nested property[{}] is null", propertyPath);
    } catch (Exception e) {
      throw new BeanPropertyReadException("e", e);
    }
    return null;
  }

  @Override
  public void setProperty(Object object, String propertyPath, Object value) throws BeanPropertyWriteException {
    try {
      BeanUtil.forced.setProperty(object, propertyPath, value);
    } catch (Exception e) {
      throw new BeanPropertyWriteException(e);
    }
  }

}
