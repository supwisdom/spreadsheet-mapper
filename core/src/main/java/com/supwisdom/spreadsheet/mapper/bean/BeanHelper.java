package com.supwisdom.spreadsheet.mapper.bean;

/**
 * 对于POJO操作的帮助工具。<br>
 * 被操作的Object必须符合JavaBean规范。<br>
 * 简单来说就是如果要访问、操作一个属性，这个属性必须有对应的可访问的setter、getter方法。<br>
 * 本类是通过属性路径来操作的，属性路径支持以下几种：
 * <ul>
 * <li>someProperty。直接的属性名。</li>
 * <li>propA.propB。嵌套属性。</li>
 * </ul>
 * Created by qianjia on 2017/3/14.
 */
public interface BeanHelper {

  /**
   * 获得对象的属性值。会在以下情况抛出异常：
   * <ul>
   * <li>找不到对应的getter方法时</li>
   * </ul>
   *
   * @param object       对象
   * @param propertyPath 属性路径。如果是嵌套属性，在任意节点上是null时，则直接返回null。
   * @return
   * @throws BeanPropertyReadException
   */
  Object getProperty(Object object, String propertyPath) throws BeanPropertyReadException;

  /**
   * 设置对象的属性值。会在以下情况抛出异常：
   * <ul>
   * <li>找不到对应的getter方法</li>
   * <li>属性路径是嵌套属性时，如果无法利用默认构造函数构造嵌套属性时。</li>
   * </ul>
   *
   * @param object       对象
   * @param propertyPath 属性路径
   * @param value        设置的值
   * @throws BeanPropertyWriteException
   */
  void setProperty(Object object, String propertyPath, Object value) throws BeanPropertyWriteException;

}
