package o2w;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.*;
import com.supwisdom.spreadsheet.mapper.o2w.DefaultObject2SheetComposer;
import com.supwisdom.spreadsheet.mapper.o2w.DefaultObject2WorkbookComposer;
import com.supwisdom.spreadsheet.mapper.o2w.Object2SheetComposer;
import com.supwisdom.spreadsheet.mapper.o2w.Object2WorkbookComposer;
import com.supwisdom.spreadsheet.mapper.o2w.converter.BooleanPropertyStringifier;
import com.supwisdom.spreadsheet.mapper.o2w.converter.LambdaPropertyStringifier;
import com.supwisdom.spreadsheet.mapper.o2w.converter.NumberPropertyStringifier;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 将对象转换成Workbook的例子
 * Created by qianjia on 2017/3/14.
 */
public class Object2WorkbookExample {

  public static void main(String[] args) {

    new Object2WorkbookExample().convertToWorkbook();

  }

  public Workbook convertToWorkbook() {

    WorkbookMeta workbookMeta = new WorkbookMetaBean();

    SheetMeta sheetMeta = new SheetMetaBean("sheet-a", 3);

    FieldMeta fieldMeta1 = new FieldMetaBean("name", 1);
    fieldMeta1.addHeaderMeta(new HeaderMetaBean(1, "name表头"));
    fieldMeta1.addHeaderMeta(new HeaderMetaBean(2, "字符串属性"));

    FieldMeta fieldMeta2 = new FieldMetaBean("age", 2);
    fieldMeta2.addHeaderMeta(new HeaderMetaBean(1, "age表头"));
    fieldMeta2.addHeaderMeta(new HeaderMetaBean(2, "数字属性"));

    FieldMeta fieldMeta3 = new FieldMetaBean("passed", 3);
    fieldMeta3.addHeaderMeta(new HeaderMetaBean(1, "passed表头"));
    fieldMeta3.addHeaderMeta(new HeaderMetaBean(2, "Boolean属性"));

    FieldMeta fieldMeta4 = new FieldMetaBean("bar.name", 4);
    fieldMeta4.addHeaderMeta(new HeaderMetaBean(1, "bar.name表头"));
    fieldMeta4.addHeaderMeta(new HeaderMetaBean(2, "嵌套属性"));

    FieldMeta fieldMeta5 = new FieldMetaBean("bar2", 5);
    fieldMeta5.addHeaderMeta(new HeaderMetaBean(1, "bar2表头"));
    fieldMeta5.addHeaderMeta(new HeaderMetaBean(2, "Lambda属性"));

    sheetMeta.addFieldMeta(fieldMeta1);
    sheetMeta.addFieldMeta(fieldMeta2);
    sheetMeta.addFieldMeta(fieldMeta3);
    sheetMeta.addFieldMeta(fieldMeta4);
    sheetMeta.addFieldMeta(fieldMeta5);

    workbookMeta.addSheetMeta(sheetMeta);

    List<List> dataOfSheets = new ArrayList<>();
    List<Foo> fooList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      fooList.add(newFoo());
    }
    dataOfSheets.add(fooList);

    Object2WorkbookComposer object2WorkbookComposer = new DefaultObject2WorkbookComposer();
    Object2SheetComposer<Foo> object2SheetComposer = new DefaultObject2SheetComposer<>();
    // 添加属性转换器
    object2SheetComposer
        .addFieldConverter(new BooleanPropertyStringifier().falseString("不及格").trueString("及格").matchField("passed"));
    object2SheetComposer.addFieldConverter(new NumberPropertyStringifier().matchField("age"));
    object2SheetComposer
        .addFieldConverter(new LambdaPropertyStringifier(property -> "利用Lambda表达式获得值").matchField("bar2"));

    Workbook workbook = object2WorkbookComposer
        .addObject2SheetComposer(object2SheetComposer)
        .compose(dataOfSheets, workbookMeta);

    return workbook;

  }

  private Foo newFoo() {

    Foo foo = new Foo();
    foo.setName(RandomStringUtils.randomAlphanumeric(10));
    foo.setAge(RandomUtils.nextInt());
    foo.setPassed(RandomUtils.nextBoolean());
    foo.getBar().setName(RandomStringUtils.randomAlphanumeric(5));
    return foo;

  }

}
