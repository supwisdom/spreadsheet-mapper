package w2o;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.*;
import com.supwisdom.spreadsheet.mapper.w2o.DefaultSheet2ObjectComposer;
import com.supwisdom.spreadsheet.mapper.w2o.DefaultWorkbook2ObjectComposer;
import com.supwisdom.spreadsheet.mapper.w2o.Sheet2ObjectComposer;
import com.supwisdom.spreadsheet.mapper.w2o.Workbook2ObjectComposer;
import com.supwisdom.spreadsheet.mapper.w2o.setter.BooleanPropertySetter;
import com.supwisdom.spreadsheet.mapper.w2o.setter.LambdaPropertySetter;
import o2w.Bar;
import o2w.Foo;
import o2w.Object2WorkbookExample;

import java.util.Collections;
import java.util.List;

/**
 * 将Workbook转换成Object的例子
 * Created by qianjia on 2017/3/14.
 */
public class Workbook2ObjectComposerExample {

  public static void main(String[] args) {

    Object2WorkbookExample object2WorkbookExample = new Object2WorkbookExample();
    // 这个是已经从对象转换成workbook
    Workbook workbook = object2WorkbookExample.convertToWorkbook();

    WorkbookMeta workbookMeta = new WorkbookMetaBean();

    // 注意，这里可以不需要HeadMeta
    SheetMeta sheetMeta = new SheetMetaBean("sheet-a", 3);
    FieldMeta fieldMeta1 = new FieldMetaBean("name", 1);
    FieldMeta fieldMeta2 = new FieldMetaBean("age", 2);
    FieldMeta fieldMeta3 = new FieldMetaBean("passed", 3);
    FieldMeta fieldMeta4 = new FieldMetaBean("bar.name", 4);
    FieldMeta fieldMeta5 = new FieldMetaBean("bar2", 5);

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);
    sheetMeta.addFieldMeta(fieldMeta3);
    sheetMeta.addFieldMeta(fieldMeta4);
    sheetMeta.addFieldMeta(fieldMeta5);

    workbookMeta.addSheetMeta(sheetMeta);

    Workbook2ObjectComposer workbook2ObjectComposer = new DefaultWorkbook2ObjectComposer();
    Sheet2ObjectComposer sheet2ObjectComposer = new DefaultSheet2ObjectComposer();
    // 定义对象工厂，用来创建对象的
    sheet2ObjectComposer.setObjectFactory((row, sheetMeta1) -> new Foo());
    // 数字、字符串类型不需要特别的 PropertySetter
    sheet2ObjectComposer.addFieldSetter(new BooleanPropertySetter(Collections.singleton("及格"), Collections.singleton("不及格")).matchField("passed"));
    sheet2ObjectComposer.addFieldSetter(new LambdaPropertySetter(propertyString -> new Bar()).matchField("bar2"));

    workbook2ObjectComposer.addSheet2ObjectComposer(sheet2ObjectComposer);

    List<List<Foo>> fooListList = (List) workbook2ObjectComposer.compose(workbook, workbookMeta);
    for (List<Foo> fooList : fooListList) {
      for (Foo foo : fooList) {
        System.out.println(foo);
      }
    }

  }

}
