package com.supwisdom.spreadsheet.mapper.o2w;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.List;

/**
 * 将List&lt;List&lt;Object&gt;&gt;转换为{@link Workbook}的工具 <br>
 * Created by hanwen on 2017/1/4.
 */
public interface Object2WorkbookComposer {

  /**
   * 添加{@link Object2SheetComposer}<br>
   * 添加顺序要和{@link #compose(List, WorkbookMeta)}里的dataOfSheets参数里的数据的顺序保持一致。
   *
   * @param object2SheetComposer {@link Object2SheetComposer}
   * @return {@link Object2WorkbookComposer}
   */
  Object2WorkbookComposer addObject2SheetComposer(Object2SheetComposer object2SheetComposer);

  /**
   * @param dataOfSheets List&lt;List&lt;Object&gt;&gt;，一个List代表一个Sheet里的数据
   * @param workbookMeta {@link WorkbookMeta}
   * @return {@link Workbook}
   */
  Workbook compose(List<List> dataOfSheets, WorkbookMeta workbookMeta);

}
