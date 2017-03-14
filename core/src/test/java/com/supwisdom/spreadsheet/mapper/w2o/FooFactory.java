package com.supwisdom.spreadsheet.mapper.w2o;

import com.supwisdom.spreadsheet.mapper.bean.Foo;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

/**
 * Created by qianjia on 2017/3/14.
 */
public class FooFactory implements ObjectFactory<Foo> {

  @Override
  public Foo create(Row row, SheetMeta sheetMeta) {
    return new Foo();
  }

}
