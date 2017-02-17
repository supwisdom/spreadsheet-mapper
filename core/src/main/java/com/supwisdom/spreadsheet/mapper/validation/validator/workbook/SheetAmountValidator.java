package com.supwisdom.spreadsheet.mapper.validation.validator.workbook;

import com.supwisdom.spreadsheet.mapper.model.core.Workbook;
import com.supwisdom.spreadsheet.mapper.model.meta.WorkbookMeta;

import java.util.Collections;
import java.util.Set;

/**
 * sheet数量校验器，不得超过给定的数值
 * Created by hanwen on 4/26/16.
 */
public class SheetAmountValidator extends WorkbookValidatorTemplate<SheetAmountValidator> {

  private int maxSize;

  public SheetAmountValidator(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  public boolean validate(Workbook workbook, WorkbookMeta workbookMeta) {
    return workbook.sizeOfSheets() == maxSize;
  }

  @Override
  public Set<Integer> getErrorSheetIndices() {
    return Collections.singleton(1);
  }

}
