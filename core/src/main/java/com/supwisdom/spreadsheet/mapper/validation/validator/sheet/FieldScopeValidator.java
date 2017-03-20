package com.supwisdom.spreadsheet.mapper.validation.validator.sheet;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;

import java.util.*;

/**
 * field限定范围校验器，比如一个{@link SheetMeta}有{@link FieldMeta} A、B、C、D，本校验器规定只能出现field A、B，那么就会验证失败。
 * 简而言之，{@link SheetMeta}的{@link FieldMeta}必须是本校验器规定的范围的子集。
 * Created by hanwen on 4/26/16.
 */
public class FieldScopeValidator extends SheetValidatorTemplate<FieldScopeValidator> {

  private Set<String> fieldScopes = new HashSet<>();

  public FieldScopeValidator(Collection<String> fieldScopes) {
    this.fieldScopes = new HashSet<>(fieldScopes);
  }

  public FieldScopeValidator(String[] fieldScopes) {
    this.fieldScopes = new HashSet<>(Arrays.asList(fieldScopes));
  }

  @Override
  public boolean validate(Sheet sheet, SheetMeta sheetMeta) {

    List<FieldMeta> fieldMetas = sheetMeta.getFieldMeta();

    for (FieldMeta fieldMeta : fieldMetas) {
      if (!fieldScopes.contains(fieldMeta.getName())) {
        return false;
      }
    }
    return true;
  }

}
