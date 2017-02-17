package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.List;

/**
 * 将{@link Workbook}转换成List&lt;List&lt;Object&gt;&gt;的工具
 * Created by hanwen on 2017/1/4.
 */
public interface Workbook2ObjectComposer {

  /**
   * 添加{@link Sheet2ObjectComposer}。
   * 如果Workbook有多个Sheet，添加的顺序应该和Workbook中的Sheet的顺序对应。
   *
   * @param sheet2ObjectComposer {@link Sheet2ObjectComposer}
   * @return 自己
   */
  Workbook2ObjectComposer addSheet2ObjectComposer(Sheet2ObjectComposer sheet2ObjectComposer);

  /**
   * 将{@link Workbook}里的{@link Sheet}转换成Object，每个{@link Sheet}有对应的List&lt;Object&gt; <br>
   * 需要注意的是：Workbook的Sheet数量、WorkbookMeta的SheetMeta数量、Sheet2ObjectComposer数量必须保持一致
   *
   * @param workbook     {@link Workbook}
   * @param workbookMeta {@link WorkbookMeta}
   * @return 所有Sheet的转换结果
   * @see Sheet2ObjectComposer#compose(Sheet, SheetMeta)
   */
  List<List> compose(Workbook workbook, WorkbookMeta workbookMeta);
  
}
