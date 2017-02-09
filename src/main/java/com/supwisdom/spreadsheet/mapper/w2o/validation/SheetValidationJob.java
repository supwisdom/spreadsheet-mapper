package com.supwisdom.spreadsheet.mapper.w2o.validation;

import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.Validator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.SingleCellValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.sheet.SheetValidator;
import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.cell.MultiCellValidator;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.row.RowValidator;

import java.util.List;

/**
 * sheet validation helper
 * <p>
 * Created by hanwen on 15-12-16.
 */
public interface SheetValidationJob {

  /**
   * @param sheetValidator {@link SheetValidator}
   * @return {@link SheetValidationJob}
   */
  SheetValidationJob addSheetValidator(SheetValidator sheetValidator);

  /**
   * @param rowValidator {@link RowValidator}
   * @return {@link SheetValidationJob}
   */
  SheetValidationJob addRowValidator(RowValidator rowValidator);

  /**
   * @param validator {@link Validator}
   * @return {@link SheetValidationJob}
   * @see SingleCellValidator
   * @see MultiCellValidator
   */
  SheetValidationJob addDependencyValidator(Validator validator);

  /**
   * execute valid
   *
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   * @return true if pass all
   */
  boolean valid(Sheet sheet, SheetMeta sheetMeta);

  /**
   * <pre>
   * message write strategy of {@link SheetValidator#getErrorMessage()} is {@link MessageWriteStrategies#TEXT_BOX}
   * message write strategy of {@link RowValidator#getErrorMessage()} &amp; {@link SingleCellValidator#getErrorMessage()} is {@link MessageWriteStrategies#COMMENT}
   * </pre>
   *
   * @return list of valid error messages
   */
  List<Message> getErrorMessages();
}
