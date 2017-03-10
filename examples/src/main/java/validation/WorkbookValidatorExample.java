package validation;

import com.supwisdom.spreadsheet.mapper.f2w.WorkbookReader;
import com.supwisdom.spreadsheet.mapper.f2w.excel.Excel2WorkbookReader;
import com.supwisdom.spreadsheet.mapper.m2f.MessageWriter;
import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriter;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMetaBean;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMetaBean;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.validation.DefaultSheetValidationJob;
import com.supwisdom.spreadsheet.mapper.validation.DefaultWorkbookValidationJob;
import com.supwisdom.spreadsheet.mapper.validation.builder.CellValidatorBatchBuilder;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.RequireValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.UniqueValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.ValueScopeParam;
import com.supwisdom.spreadsheet.mapper.validation.builder.cell.ValueScopeValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.LambdaUnionCellValidatorFactory;
import com.supwisdom.spreadsheet.mapper.validation.builder.unioncell.LambdaUnionCellValidatorParam;
import com.supwisdom.spreadsheet.mapper.validation.validator.workbook.SheetAmountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by qianjia on 2017/3/10.
 */
public class WorkbookValidatorExample {

  private static final Logger LOGGER = LoggerFactory.getLogger(WorkbookValidatorExample.class);

  public static void main(String[] args) throws IOException {

    // 先从excel文件中读取成 Workbook
    WorkbookReader workbookReader = new Excel2WorkbookReader();
    Workbook workbook;
    try (InputStream inputStream = WorkbookValidatorExample.class.getResourceAsStream("test.xlsx")) {
      workbook = workbookReader.read(inputStream);
    }

    // 构造Workbook元信息，包含信息：有几个sheet，每个sheet里有包含有哪些field，数据是从哪行开始的
    WorkbookMetaBean workbookMeta = new WorkbookMetaBean();

    // 第一个Sheet的元信息，这里定义了真正的数据是从第4行开始的
    // 这样在校验的时候会跳过前面3行，因为前面3行是表头
    SheetMetaBean sheetMeta = new SheetMetaBean(4);

    // 添加Field元信息，field的名字可以和excel中的名字不一样
    // 不过为了便于理解，一般来说都是一样的
    // field的名字在后面注册校验器的时候有用
    sheetMeta.addFieldMeta(new FieldMetaBean("code", 1));
    sheetMeta.addFieldMeta(new FieldMetaBean("name", 2));
    sheetMeta.addFieldMeta(new FieldMetaBean("gender", 3));
    sheetMeta.addFieldMeta(new FieldMetaBean("xueli", 4));
    sheetMeta.addFieldMeta(new FieldMetaBean("identityCardType", 5));
    sheetMeta.addFieldMeta(new FieldMetaBean("identityCardNo", 6));
    sheetMeta.addFieldMeta(new FieldMetaBean("grade", 7));
    sheetMeta.addFieldMeta(new FieldMetaBean("college", 8));
    sheetMeta.addFieldMeta(new FieldMetaBean("major", 9));
    sheetMeta.addFieldMeta(new FieldMetaBean("adminclass", 10));

    workbookMeta.addSheetMeta(sheetMeta);

    // 构建Workbook校验工作
    DefaultWorkbookValidationJob workbookValidationJob = new DefaultWorkbookValidationJob();
    // 校验workbook的sheet数量等于1
    workbookValidationJob.addValidator(new SheetAmountValidator(1));

    DefaultSheetValidationJob sheetValidationJob = new DefaultSheetValidationJob();
    workbookValidationJob.addSheetValidationJob(sheetValidationJob);

    CellValidatorBatchBuilder validatorBuilder = new CellValidatorBatchBuilder();
    validatorBuilder
        // 给这些Field添加必填校验
        .start(RequireValidatorFactory.getInstance())
        .matchFields("code", "gender", "xueli", "identityCardType", "identityCardNo", "grade", "college", "major",
            "adminclass")
        .errorMessage("必填")
        .end()

        // 性别 只能填男、女
        .start(ValueScopeValidatorFactory.getInstance())
        .matchFields("gender")
        .param(new ValueScopeParam(new String[] { "男", "女" }))
        .errorMessage("只能填写：男、女")
        .end()

        // 是否学历生 只能填 是、否
        .start(ValueScopeValidatorFactory.getInstance())
        .matchFields("xueli")
        .param(new ValueScopeParam(new String[] { "是", "否" }))
        .errorMessage("只能填写：是、否")
        .end()

        // 证件类型 只能填 居民身份证、护照、港澳通行证
        .start(ValueScopeValidatorFactory.getInstance())
        .matchFields("identityCardType")
        .param(new ValueScopeParam(new String[] { "居民身份证", "护照", "港澳通行证" }))
        .errorMessage("只能填写。居民身份证、护照、港澳通行证")
        .end()

        // 证件号码 如果是 居民身份证，号码必须18位
        // 因为是联合几个Cell的校验，因此要定义group
        .start(LambdaUnionCellValidatorFactory.getInstance())
        .group("identity")
        .matchFields("identityCardType", "identityCardNo")
        .dependsOn("identityCardType")
        .param(new LambdaUnionCellValidatorParam((cells, metas) -> {
          String idcardType = cells.get(0).getValue();
          String idcardNo = cells.get(1).getValue();
          if (idcardType.equals("居民身份证")) {
            return idcardNo.length() == 18;
          }
          return true;
        }))
        .errorMessage("证件号码长度不符合")
        .end()

        // 学号、证件号码 在excel文件里唯一
        .start(UniqueValidatorFactory.getInstance())
        .matchFields("code", "identityCardNo")
        .errorMessage("不唯一")
        .end()
    
    ;
    validatorBuilder.addToSheetValidationJob(sheetValidationJob);

    // 开始校验
    boolean valid = workbookValidationJob.validate(workbook, workbookMeta);
    List<Message> workbookErrors = workbookValidationJob.getErrorMessages();

    MessageWriter messageWriter = null;
    try (InputStream resourceAsStream = WorkbookValidatorExample.class.getResourceAsStream("test.xlsx")) {
      // 读取excel内容
      messageWriter = new ExcelMessageWriter(resourceAsStream);

      File tempFile = File.createTempFile("test-valid-result", ".xlsx");

      try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
        messageWriter.write(workbookErrors, outputStream);
      }
      LOGGER.info("test.xlsx validation result: " + valid + ". Output file: " + tempFile.getAbsolutePath());

    }

  }

}
