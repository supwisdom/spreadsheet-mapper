package com.supwisdom.spreadsheet.mapper.validation.validator.workbook;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.Collections;
import java.util.Set;

/**
 * sheet数量校验器，必须等于给定的数值
 * Created by hanwen on 4/26/16.
 */
public class SheetAmountValidator extends WorkbookValidatorTemplate<SheetAmountValidator> {

  private int sheetAmount;

  public SheetAmountValidator(int sheetAmount) {
    this.sheetAmount = sheetAmount;
  }

  @Override
  public boolean validate(Workbook workbook, WorkbookMeta workbookMeta) {
    return workbook.sizeOfSheets() == sheetAmount;
  }

  @Override
  public Set<Integer> getErrorSheetIndices() {
    return Collections.singleton(1);
  }

}
