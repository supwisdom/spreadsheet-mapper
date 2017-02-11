package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;

import java.util.List;

/**
 * sheet validation job
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
   * @param cellValidator {@link CellValidator}
   * @return {@link SheetValidationJob}
   */
  SheetValidationJob addCellValidator(CellValidator cellValidator);

  /**
   * @param unionCellValidator
   * @return
   */
  SheetValidationJob addUnionCellValidator(UnionCellValidator unionCellValidator);

  /**
   * execute validate
   *
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   * @return true if pass all
   */
  boolean validate(Sheet sheet, SheetMeta sheetMeta);

  /**
   * <pre>
   * message write strategy of {@link SheetValidator#getErrorMessage()} is {@link MessageWriteStrategies#TEXT_BOX}
   * message write strategy of {@link RowValidator#getErrorMessage()} &amp; {@link CellValidator#getErrorMessage()} is {@link MessageWriteStrategies#COMMENT}
   * </pre>
   *
   * @return list of validate error messages
   */
  List<Message> getErrorMessages();
}
