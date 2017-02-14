package com.supwisdom.spreadsheet.mapper.utils;

import java.lang.reflect.Field;

/**
 * object field utils
 * <p>
 * Created by hanwen on 15-12-18.
 */
public class FieldUtils {

  private FieldUtils() {
    // default constructor
  }

  /**
   * <pre>
   * get field if can't find return null.
   * supported private, private final, protected, protected final, public, public final.
   * </pre>
   *
   * @param clazz clazz
   * @param field field name of object
   * @return {@link Field}
   */
  public static Field getField(Class clazz, String field) {
    try {
      return clazz.getDeclaredField(field);
    } catch (NoSuchFieldException e) {
      if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
        return getField(clazz.getSuperclass(), field);
      }
    }
    return null;
  }

  /**
   * get field type if can't find return null.
   *
   * @param clazz  clazz
   * @param fields eg: [objectB, objectA, name] is objectB.objectA.name
   * @return {@link Field#getType()}
   */
  public static Class getFieldType(Class clazz, String[] fields) {
    if (fields.length == 0 || clazz == null) {
      return null;
    }
    if (fields.length > 1) {
      String[] newFields = new String[fields.length - 1];
      System.arraycopy(fields, 1, newFields, 0, newFields.length);
      return getFieldType(getFieldType(clazz, fields[0]), newFields);
    }
    return getFieldType(clazz, fields[0]);
  }

  /**
   * @param clazz     clazz
   * @param fieldName field name of object
   * @return {@link Field#getType()}
   * @see #getFieldType(Class, String[])
   */
  public static Class getFieldType(Class clazz, String fieldName) {

    Field field = getField(clazz, fieldName);

    if (field == null) {
      return null;
    }

    return field.getType();
  }
}
