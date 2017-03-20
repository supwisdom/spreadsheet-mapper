package com.supwisdom.spreadsheet.mapper.validation.validator.sheet;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.FieldMeta;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 必须提供某些field校验器，比如一个{@link SheetMeta}有{@link FieldMeta} A、C，本校验器规定必须有field A、B，那么就会验证失败。
 * 简而言之，本校验器规定的范围必须是{@link SheetMeta}的{@link FieldMeta}的子集。
 * Created by hanwen on 4/26/16.
 */
public class RequireFieldValidator extends SheetValidatorTemplate<RequireFieldValidator> {

  private Set<String> requireFields = new HashSet<>();

  public RequireFieldValidator(Collection<String> requireFields) {
    this.requireFields = new HashSet<>(requireFields);
  }

  public RequireFieldValidator(String[] requireFields) {
    this.requireFields = new HashSet<>(Arrays.asList(requireFields));
  }

  @Override
  public boolean validate(Sheet sheet, SheetMeta sheetMeta) {

    List<FieldMeta> fieldMetas = sheetMeta.getFieldMeta();

    List<String> fields = new ArrayList<>();
    for (FieldMeta fieldMeta : fieldMetas) {
      fields.add(fieldMeta.getName());
    }

    return CollectionUtils.subtract(requireFields, fields).isEmpty();

  }

}
