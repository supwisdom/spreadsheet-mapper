package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.listener.CellProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.RowProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.listener.SheetProcessListener;
import com.supwisdom.spreadsheet.mapper.w2o.setter.PropertySetter;

import java.util.List;

/**
 * {@link Sheet}到List&lt;Object&gt;的转换器
 * Created by hanwen on 2016/12/28.
 */
public interface Sheet2ObjectComposer<T> {

  /**
   * 设置忽略的field。如果设置了ignore fields，那么这些fields肯定不会被设置值。
   *
   * @param field       field
   * @param otherFields 其他field
   */
  void ignoreFields(String field, String... otherFields);

  /**
   * 添加{@link PropertySetter}，一个field只能有一个FieldSetter。<br>
   * 在{@link #compose(Sheet, SheetMeta)}之前必须要设置
   *
   * @param propertySetter {@link PropertySetter}
   * @return 自己
   */
  Sheet2ObjectComposer<T> addFieldSetter(PropertySetter propertySetter);

  /**
   * 设置{@link ObjectFactory}。<br>
   * 在{@link #compose(Sheet, SheetMeta)}之前必须要设置
   *
   * @param objectFactory {@link ObjectFactory}
   * @return 自己
   */
  Sheet2ObjectComposer<T> setObjectFactory(ObjectFactory<T> objectFactory);

  /**
   * @param sheetProcessListener {@link SheetProcessListener}
   * @return 自己
   */
  Sheet2ObjectComposer<T> setSheetProcessorListener(SheetProcessListener<T> sheetProcessListener);

  /**
   * @param rowProcessListener {@link RowProcessListener}
   * @return 自己
   */
  Sheet2ObjectComposer<T> setRowProcessorListener(RowProcessListener<T> rowProcessListener);

  /**
   * @param cellProcessListener {@link CellProcessListener}
   * @return 自己
   */
  Sheet2ObjectComposer<T> setCellProcessorListener(CellProcessListener<T> cellProcessListener);

  /**
   * 将Sheet转换成List&lt;Object&gt;
   *
   * @param sheet     {@link Sheet}，这里面包含{@link Row}数据
   * @param sheetMeta {@link SheetMeta}，这里包含{@link FieldMeta}，{@link FieldMeta}是和Object里的每个property对应的。
   *                  也就是说，如果没有{@link FieldMeta}，那么Object的property是不会被set值的
   * @return Object列表
   */
  List<T> compose(Sheet sheet, SheetMeta sheetMeta);

}
