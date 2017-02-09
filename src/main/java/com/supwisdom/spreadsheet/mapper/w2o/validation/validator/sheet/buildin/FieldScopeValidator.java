package com.supwisdom.spreadsheet.mapper.w2o.validation.validator.sheet.buildin;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.w2o.validation.validator.sheet.SheetValidator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * field scope validator,
 *
 * it useful when you only want after some special fields of a object, this validator can prevent unexpected things, for security.
 *
 * eg : class A has fields [A, other fields...].
 * if you only want modify A, you can supplied the {@link FieldScopeValidator#fieldScopes} as [A], when the excel files fields has others,
 * this validator will get false.
 * </pre>
 * Created by hanwen on 4/26/16.
 */
public class FieldScopeValidator implements SheetValidator {

  private Set<String> fieldScopes = new HashSet<>();

  private String errorMessage;

  public FieldScopeValidator fieldScopes(String... fieldScopes) {
    if (fieldScopes == null) {
      return this;
    }
    Collections.addAll(this.fieldScopes, fieldScopes);
    return this;
  }

  public FieldScopeValidator errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public boolean valid(Sheet sheet, SheetMeta sheetMeta) {

    List<FieldMeta> fieldMetas = sheetMeta.getFieldMetas();

    for (FieldMeta fieldMeta : fieldMetas) {
      if (!fieldScopes.contains(fieldMeta.getName())) {
        return false;
      }
    }
    return true;
  }

}
