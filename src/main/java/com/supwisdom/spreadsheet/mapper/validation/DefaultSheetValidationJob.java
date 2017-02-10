package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.msg.MessageBean;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.validation.engine.ValidatorDependencyGraphHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.supwisdom.spreadsheet.mapper.model.core.Row;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.validation.engine.ValidatorCyclicDependencyChecker;
import com.supwisdom.spreadsheet.mapper.validation.engine.ValidationEngine;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.Validator;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;

import java.util.*;

/**
 * Created by hanwen on 15-12-16.
 */
public class DefaultSheetValidationJob implements SheetValidationJob {

  /*===============
    validators
   ================*/
  private List<SheetValidator> sheetValidators = new ArrayList<>();
  private List<RowValidator> rowValidators = new ArrayList<>();
  // dependency validators one group corresponding multi validators
  private LinkedHashMap<String, List<Validator>> dependencyValidators = new LinkedHashMap<>();

  private List<Message> errorMessages = new ArrayList<>();

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
  public SheetValidationJob addDependencyValidator(Validator validator) {
    if (validator == null) {
      throw new IllegalArgumentException("dependency validator can not be null");
    }
    String group = validator.getGroup();
    if (StringUtils.isBlank(group)) {
      throw new WorkbookValidateException("dependency validator[" + validator.getClass().getName() + "] group can not be null");
    }

    if (!dependencyValidators.containsKey(group)) {
      dependencyValidators.put(group, new ArrayList<Validator>());
    }
    dependencyValidators.get(group).add(validator);
    return this;
  }

  @Override
  public List<Message> getErrorMessages() {
    return errorMessages;
  }

  @Override
  public boolean valid(Sheet sheet, SheetMeta sheetMeta) {
    // check dependency of this sheet
    checkValidatorGroupDependency();

    if (!validSheet(sheet, sheetMeta)) {
      return false;
    }

    boolean result = true;
    for (Row row : sheet.getRows()) {

      if (!validRow(row, sheetMeta)) {
        result = false;
        continue;
      }

      if (row.getIndex() >= sheetMeta.getDataStartRowIndex()) {
        if (!validDataRow(row, sheetMeta)) {
          result = false;
        }
      }
    }

    return result;
  }

  /**
   * check if dependency correct
   */
  private void checkValidatorGroupDependency() {

    LinkedHashMap<String, LinkedHashSet<String>> vGraph = ValidatorDependencyGraphHelper.buildVGraph(dependencyValidators);
    Set<String> allGroups = vGraph.keySet();

    for (Map.Entry<String, LinkedHashSet<String>> entry : vGraph.entrySet()) {
      String group = entry.getKey();
      Set<String> dependsOn = entry.getValue();

      Collection missingGroups = CollectionUtils.subtract(dependsOn, allGroups);
      if (!missingGroups.isEmpty()) {
        throw new WorkbookValidateException("[" + group + "]depends on missing group:" + missingGroups.toString());
      }
    }

    ValidatorCyclicDependencyChecker validatorCyclicDependencyChecker = new ValidatorCyclicDependencyChecker(vGraph);
    if (validatorCyclicDependencyChecker.cycling()) {
      throw new WorkbookValidateException("dependency exists cycle:" + validatorCyclicDependencyChecker.getCycle().toString());
    }
  }

  /*=========================
   below is internal valid
   ==========================*/
  private boolean validSheet(Sheet sheet, SheetMeta sheetMeta) {
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

  private boolean validRow(Row row, SheetMeta sheetMeta) {
    boolean result = true;

    for (RowValidator validator : rowValidators) {

      if (!validator.valid(row, sheetMeta)) {
        result = false;

        String errorMessage = validator.getErrorMessage();

        Set<String> messageOnFields = validator.getMessageOnFields();
        if (StringUtils.isNotBlank(errorMessage) && CollectionUtils.isNotEmpty(messageOnFields)) {

          for (String messageOnField : messageOnFields) {

            FieldMeta fieldMeta = sheetMeta.getFieldMeta(messageOnField);
            errorMessages.add(new MessageBean(MessageWriteStrategies.COMMENT, errorMessage, row.getSheet().getIndex(), row.getIndex(), fieldMeta.getColumnIndex()));
          }
        }
      }
    }

    return result;
  }

  private boolean validDataRow(Row row, SheetMeta sheetMeta) {
    ValidationEngine validationEngine = new ValidationEngine(dependencyValidators);

    boolean result = validationEngine.valid(row, sheetMeta);
    errorMessages.addAll(validationEngine.getErrorMessages());

    return result;
  }

}
