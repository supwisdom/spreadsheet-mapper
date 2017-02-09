package com.supwisdom.spreadsheet.mapper.w2o.validation;

import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import org.apache.commons.collections.CollectionUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.workbook.WorkbookValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwen on 2017/1/4.
 */
public class DefaultWorkbookValidationHelper implements WorkbookValidationHelper {

  private List<WorkbookValidator> workbookValidators = new ArrayList<>();

  private List<SheetValidationHelper> sheetValidationHelpers = new ArrayList<>();

  private List<Message> errorMessages = new ArrayList<>();

  @Override
  public WorkbookValidationHelper addWorkbookValidator(WorkbookValidator workbookValidator) {
    if (workbookValidator == null) {
      throw new IllegalArgumentException("workbook validator can not be null");
    }

    workbookValidators.add(workbookValidator);
    return this;
  }

  @Override
  public WorkbookValidationHelper addSheetValidationHelper(SheetValidationHelper sheetValidationHelper) {
    if (sheetValidationHelper == null) {
      throw new IllegalArgumentException("sheet validation helper can not be null");
    }

    sheetValidationHelpers.add(sheetValidationHelper);
    return this;
  }

  @Override
  public boolean valid(Workbook workbook, WorkbookMeta workbookMeta) {
    int sizeOfSheets = workbook.sizeOfSheets();
    int sizeOfSheetMetas = workbookMeta.sizeOfSheetMetas();
    int sizeOfHelper = sheetValidationHelpers.size();

    if (sizeOfSheets != sizeOfSheetMetas) {
      throw new WorkbookValidateException("workbook's sheet size[" + sizeOfSheets + "] not equals workbook meta's sheet meta size[" + sizeOfSheetMetas + "]");
    }
    if (sizeOfSheets != sizeOfHelper) {
      throw new WorkbookValidateException("workbook's sheet size[" + sizeOfSheets + "] not equals sheet validation helper size[" + sizeOfHelper + "]");
    }

    validWorkbook(workbook, workbookMeta);

    if (CollectionUtils.isNotEmpty(errorMessages)) {
      return false;
    }

    boolean sheetValidResult = true;

    for (int i = 1; i <= sizeOfSheets; i++) {

      SheetValidationHelper sheetValidationHelper = sheetValidationHelpers.get(i - 1);
      Sheet sheet = workbook.getSheet(i);
      SheetMeta sheetMeta = workbookMeta.getSheetMeta(i);

      if (!sheetValidationHelper.valid(sheet, sheetMeta)) {
        errorMessages.addAll(sheetValidationHelper.getErrorMessages());
        sheetValidResult = false;
      }
    }

    return sheetValidResult;
  }

  @Override
  public List<Message> getErrorMessages() {
    return errorMessages;
  }

  /*==============
    workbook valid
   ===============*/
  private void validWorkbook(Workbook workbook, WorkbookMeta workbookMeta) {

    for (WorkbookValidator validator : workbookValidators) {
      if (!validator.valid(workbook, workbookMeta) && validator.getMessageOnSheet() != null) {

        errorMessages.add(new MessageBean(MessageWriteStrategies.TEXT_BOX, validator.getErrorMessage(), validator.getMessageOnSheet()));
      }
    }

  }
}
