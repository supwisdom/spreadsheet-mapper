package spreadsheet.mapper.w2o.validator.sheet;

import spreadsheet.mapper.model.meta.SheetMeta;
import spreadsheet.mapper.w2o.validator.Validator;
import spreadsheet.mapper.model.core.Sheet;

/**
 * sheet validator, after workbook validators, if workbook validators failure, sheet validators will skip.
 * <p>
 * Created by hanwen on 2016/12/23.
 */
public interface SheetValidator extends Validator {

  /**
   * valid supplied excel sheet
   *
   * @param sheet     sheet
   * @param sheetMeta sheet meta
   * @return true if pass
   */
  boolean valid(Sheet sheet, SheetMeta sheetMeta);
}