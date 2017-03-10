package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.m2f.excel.ExcelMessageWriterStrategies;
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
public class DefaultSheetValidationJob implements SheetValidationJob<DefaultSheetValidationJob> {

  private List<SheetValidator> sheetValidators = new ArrayList<>();

  private List<RowValidator> rowValidators = new ArrayList<>();

  private List<Dependant> cellValidators = new ArrayList<>();

  private List<Message> errorMessages = new ArrayList<>();

  private transient CellGroupValidationEngine cellGroupValidationEngine;

  @Override
  public DefaultSheetValidationJob addValidator(SheetValidator sheetValidator) {
    if (sheetValidator == null) {
      throw new IllegalArgumentException("sheet validator can not be null");
    }
    sheetValidators.add(sheetValidator);
    return this;
  }

  @Override
  public DefaultSheetValidationJob addValidator(RowValidator rowValidator) {
    if (rowValidator == null) {
      throw new IllegalArgumentException("row validator can not be null");
    }
    rowValidators.add(rowValidator);
    return this;
  }

  @Override
  public DefaultSheetValidationJob addValidator(CellValidator cellValidator) {

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
  public DefaultSheetValidationJob addValidator(UnionCellValidator unionCellValidator) {

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

  /**
   * <ul>
   *   <li>{@link SheetValidator}校验失败的消息是{@link ExcelMessageWriterStrategies#TEXT_BOX}</li>
   *   <li>{@link RowValidator}校验失败的消息是{@link ExcelMessageWriterStrategies#COMMENT}，在每个{@link RowValidator#getErrorFields()}上</li>
   *   <li>{@link CellValidator}校验失败的消息是{@link ExcelMessageWriterStrategies#COMMENT}，在失败的field上</li>
   *   <li>{@link UnionCellValidator}校验失败的消息是{@link ExcelMessageWriterStrategies#COMMENT}，在失败的field上</li>
   * </ul>
   *
   */
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

      if (!validator.validate(sheet, sheetMeta)) {
        result = false;

        String errorMessage = validator.getErrorMessage();

        if (StringUtils.isNotBlank(errorMessage)) {

          errorMessages.add(new MessageBean(ExcelMessageWriterStrategies.TEXT_BOX, errorMessage, sheet.getIndex()));
        }
      }
    }

    return result;
  }

  private boolean executeRowValidators(Row row, SheetMeta sheetMeta) {

    boolean result = true;

    for (RowValidator validator : rowValidators) {

      if (!validator.validate(row, sheetMeta)) {
        result = false;

        String errorMessage = validator.getErrorMessage();

        Set<String> messageOnFields = validator.getErrorFields();
        if (StringUtils.isNotBlank(errorMessage) && CollectionUtils.isNotEmpty(messageOnFields)) {

          for (String messageOnField : messageOnFields) {

            FieldMeta fieldMeta = sheetMeta.getFieldMeta(messageOnField);
            errorMessages.add(
                new MessageBean(ExcelMessageWriterStrategies.COMMENT, errorMessage, row.getSheet().getIndex(), row.getIndex(),
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
