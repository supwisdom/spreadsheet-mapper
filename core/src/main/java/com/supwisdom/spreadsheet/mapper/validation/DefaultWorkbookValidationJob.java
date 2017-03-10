package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.validation.validator.workbook.WorkbookValidator;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/4.
 */
public class DefaultWorkbookValidationJob implements WorkbookValidationJob<DefaultWorkbookValidationJob> {

  private List<WorkbookValidator> workbookValidators = new ArrayList<>();

  private List<SheetValidationJob> sheetValidationJobs = new ArrayList<>();

  private List<Message> errorMessages = new ArrayList<>();

  @Override
  public DefaultWorkbookValidationJob addValidator(WorkbookValidator workbookValidator) {
    if (workbookValidator == null) {
      throw new IllegalArgumentException("workbook validator can not be null");
    }

    workbookValidators.add(workbookValidator);
    return this;
  }

  @Override
  public DefaultWorkbookValidationJob addSheetValidationJob(SheetValidationJob sheetValidationJob) {
    if (sheetValidationJob == null) {
      throw new IllegalArgumentException("sheet validation job can not be null");
    }

    sheetValidationJobs.add(sheetValidationJob);
    return this;
  }

  @Override
  public boolean validate(Workbook workbook, WorkbookMeta workbookMeta) {
    int sizeOfSheets = workbook.sizeOfSheets();
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfHelper = sheetValidationJobs.size();

    if (sizeOfSheets != sizeOfSheetMetas) {
      throw new WorkbookValidateException(
          "workbook's sheet size[" + sizeOfSheets + "] not equals workbook meta's sheet meta size[" + sizeOfSheetMetas
              + "]");
    }
    if (sizeOfSheets != sizeOfHelper) {
      throw new WorkbookValidateException(
          "workbook's sheet size[" + sizeOfSheets + "] not equals sheet validation job size[" + sizeOfHelper + "]");
    }

    validWorkbook(workbook, workbookMeta);

    if (CollectionUtils.isNotEmpty(errorMessages)) {
      return false;
    }

    boolean sheetValidResult = true;

    for (int i = 1; i <= sizeOfSheets; i++) {

      SheetValidationJob sheetValidationJob = sheetValidationJobs.get(i - 1);
      Sheet sheet = workbook.getSheet(i);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(i);

      if (!sheetValidationJob.validate(sheet, sheetMeta)) {
        errorMessages.addAll(sheetValidationJob.getErrorMessages());
        sheetValidResult = false;
      }
    }

    return sheetValidResult;
  }

  /**
   * <ul>
   *   <li>{@link WorkbookValidator}校验失败的消息是{@link ExcelMessageWriteStrategies#TEXT_BOX}，在每个{@link WorkbookValidator#getErrorSheetIndices()}上</li>
   *   <li>{@link DefaultSheetValidationJob#getErrorMessages()}</li>
   * </ul>
   */
  @Override
  public List<Message> getErrorMessages() {
    return errorMessages;
  }

  /*==============
    workbook validate
   ===============*/
  private void validWorkbook(Workbook workbook, WorkbookMeta workbookMeta) {

    for (WorkbookValidator validator : workbookValidators) {
      if (!validator.validate(workbook, workbookMeta)) {

        for (Integer sheetIndex : validator.getErrorSheetIndices()) {
          errorMessages.add(new MessageBean(ExcelMessageWriteStrategies.TEXT_BOX, validator.getErrorMessage(), sheetIndex));
        }

      }
    }

  }
}
