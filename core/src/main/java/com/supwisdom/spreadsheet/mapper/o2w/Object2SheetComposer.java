package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.o2w.converter.PropertyStringifier;
import org.apache.poi.ss.usermodel.CellType;

import java.util.List;

/**
 * 将List&lt;Object&gt;转换为{@link Sheet}的工具 <br>
 * Created by hanwen on 15-12-16.
 */
public interface Object2SheetComposer<T> {

  /**
   * 添加{@link PropertyStringifier}。<br>
   * {@link PropertyStringifier#getMatchField()} 不能重复
   *
   * @param propertyStringifier {@link PropertyStringifier}
   * @return {@link Object2SheetComposer}
   */
  Object2SheetComposer<T> addFieldConverter(PropertyStringifier propertyStringifier);


  /**
   * 添加cellType属性
   *
   * @param field
   * @param cellType
   * @return
   */
  Object2SheetComposer<T> addFieldCellType(String field, CellType cellType);

  /**
   * @param dataOfSheet List&lt;Object&gt;，数据，可以为null
   * @param sheetMeta   {@link SheetMeta}
   * @return {@link Sheet}
   */
  Sheet compose(List<T> dataOfSheet, SheetMeta sheetMeta);

}
