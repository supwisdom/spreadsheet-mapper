package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.validation.engine.CellGroupValidationEngine;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hanwen on 15-12-16.
 */
public class DefaultSheetValidationJob implements SheetValidationJob {

  private List<SheetValidator> sheetValidators = new ArrayList<>();

  private List<RowValidator> rowValidators = new ArrayList<>();

  private List<Dependant> cellValidators = new ArrayList<>();

  private List<Message> errorMessages = new ArrayList<>();

  private transient CellGroupValidationEngine cellGroupValidationEngine;

  @Override
  public SheetValidationJob addSheetValidator(SheetValidator sheetValidator) {
    if (sheetValidator == null) {
      throw new IllegalArgumentException("sheet validator can not be null");
    }
    sheetValidators.add(sheetValidator);
    return this;
  }

  @Override
  public SheetValidationJob addRowValidator(RowValidator rowValidator) {
    if (rowValidator == null) {
      throw new IllegalArgumentException("row validator can not be null");
    }
    rowValidators.add(rowValidator);
    return this;
  }

  @Override
  public SheetValidationJob addCellValidator(CellValidator cellValidator) {

    if (cellValidator == null) {
      throw new IllegalArgumentException("Null cellValidator");
    }

    String group = cellValidator.getGroup();
    if (StringUtils.isBlank(group)) {
      throw new WorkbookValidateException("CellValidator[" + cellValidator.getClass().getName() + "]'s group is null");
    }

    this.cellValidators.add(cellValidator);
    return this;

  }

  @Override
  public SheetValidationJob addUnionCellValidator(UnionCellValidator unionCellValidator) {

    if (unionCellValidator == null) {
      throw new IllegalArgumentException("Null unionCellValidator");
    }

    String group = unionCellValidator.getGroup();
    if (StringUtils.isBlank(group)) {
      throw new WorkbookValidateException(
          "UnionCellValidator[" + unionCellValidator.getClass().getName() + "]'s group is null");
    }

    this.cellValidators.add(unionCellValidator);
    return this;

  }

  @Override
  public List<Message> getErrorMessages() {
    return errorMessages;
  }

  @Override
  public boolean validate(Sheet sheet, SheetMeta sheetMeta) {

    if (!executeSheetValidators(sheet, sheetMeta)) {
      return false;
    }

    cellGroupValidationEngine = new CellGroupValidationEngine(sheetMeta, cellValidators);
    cellGroupValidationEngine.initialize();

    boolean result = true;
    for (Row row : sheet.getRows()) {

      if (!executeRowValidators(row, sheetMeta)) {
        result = false;
        continue;
      }

      if (row.getIndex() >= sheetMeta.getDataStartRowIndex()) {
        if (!executeCellValidators(row, sheetMeta)) {
          result = false;
        }
      }
    }

    return result;
  }

  private boolean executeSheetValidators(Sheet sheet, SheetMeta sheetMeta) {
    boolean result = true;

    for (SheetValidator validator : sheetValidators) {

      if (!validator.valid(sheet, sheetMeta)) {
        result = false;

        String errorMessage = validator.getErrorMessage();

        if (StringUtils.isNotBlank(errorMessage)) {

          errorMessages.add(new MessageBean(MessageWriteStrategies.TEXT_BOX, errorMessage, sheet.getIndex()));
        }
      }
    }

    return result;
  }

  private boolean executeRowValidators(Row row, SheetMeta sheetMeta) {
    boolean result = true;

    for (RowValidator validator : rowValidators) {

      if (!validator.valid(row, sheetMeta)) {
        result = false;

        String errorMessage = validator.getErrorMessage();

        Set<String> messageOnFields = validator.getMessageOnFields();
        if (StringUtils.isNotBlank(errorMessage) && CollectionUtils.isNotEmpty(messageOnFields)) {

          for (String messageOnField : messageOnFields) {

            FieldMeta fieldMeta = sheetMeta.getFieldMeta(messageOnField);
            errorMessages.add(
                new MessageBean(MessageWriteStrategies.COMMENT, errorMessage, row.getSheet().getIndex(), row.getIndex(),
                    fieldMeta.getColumnIndex()));
          }
        }
      }
    }

    return result;
  }

  private boolean executeCellValidators(Row row, SheetMeta sheetMeta) {

    boolean result = cellGroupValidationEngine.validate(row, sheetMeta);
    errorMessages.addAll(cellGroupValidationEngine.getErrorMessages());

    return result;
  }

}
