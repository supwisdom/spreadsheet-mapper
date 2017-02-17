package com.supwisdom.spreadsheet.mapper.validation;

import com.supwisdom.spreadsheet.mapper.model.core.Sheet;
import com.supwisdom.spreadsheet.mapper.model.meta.SheetMeta;
import com.supwisdom.spreadsheet.mapper.model.msg.Message;
import com.supwisdom.spreadsheet.mapper.model.msg.MessageWriteStrategies;
import com.supwisdom.spreadsheet.mapper.validation.validator.Dependant;
import com.supwisdom.spreadsheet.mapper.validation.validator.cell.CellValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.row.RowValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.sheet.SheetValidator;
import com.supwisdom.spreadsheet.mapper.validation.validator.unioncell.UnionCellValidator;

import java.util.List;

/**
 * 工作表校验工作
 * Created by hanwen on 15-12-16.
 */
public interface SheetValidationJob<T extends SheetValidationJob<T>> {

  /**
   * 添加{@link SheetValidator}
   *
   * @param sheetValidator {@link SheetValidator}
   * @return 自己
   */
  T addValidator(SheetValidator sheetValidator);

  /**
   * 添加 {@link RowValidator}
   *
   * @param rowValidator {@link RowValidator}
   * @return 自己
   */
  T addValidator(RowValidator rowValidator);

  /**
   * 添加 {@link CellValidator}
   * <p>
   *   {@link CellValidator}继承了{@link Dependant}，它定义了自己属于哪个group、依赖于哪些group。
   *   所以当添加多个同group的{@link CellValidator}的时候，会追加到这个group里。
   * </p>
   *
   * 需要注意的是：
   * <ol>
   *   <li>同group内会同时存在{@link CellValidator}和{@link UnionCellValidator}</li>
   *   <li>同group内的Validator的执行顺序和添加顺序相同</li>
   *   <li>同group内前面的Validator失败了，后面的是不会执行的</li>
   *   <li>同group内的Validator失败了，则整个group失败</li>
   *   <li>group的添加顺序由这个group的第一个Validator添加时的顺序决定</li>
   *   <li>添加的Validator必须设定group</li>
   * </ol>
   * @param cellValidator {@link CellValidator}
   * @return 自己
   */
  T addValidator(CellValidator cellValidator);

  /**
   * 添加 {@link UnionCellValidator}。参考：{@link #addValidator(CellValidator)}
   *
   * @param unionCellValidator {@link UnionCellValidator}
   * @return 自己
   */
  T addValidator(UnionCellValidator unionCellValidator);

  /**
   * 执行校验。执行顺序是这样的：
   * <ol>
   * <li>{@link SheetValidator}</li>
   * <li>针对每行执行{@link RowValidator}</li>
   * <li>针对每行执行{@link CellValidator}和{@link UnionCellValidator}</li>
   * </ol>
   *
   * 如果前一步骤失败，那么就不会执行后面的步骤。其中第三步的内部逻辑是这样的：
   *
   * <p>
   * CellValidator和UnionCellValidator都继承了{@link Dependant}，它们都定义了自己属于哪个group、依赖于哪些group。
   * 我们会根据这些信息构建一个group和group之间的依赖关系。
   * </p>
   * 基于这个依赖关系，我们会这样执行：
   * <ol>
   *   <li>按照group的添加顺序执行</li>
   *   <li>找到这个group依赖的其他group，先执行它们</li>
   *   <li>
   *     根据依赖的group的执行情况，有不同的处理方式
   *     <ul>
   *       <li>如果没有依赖的group，那么执行自己</li>
   *       <li>如果依赖的group全部成功，那么执行自己</li>
   *       <li>如果依赖的group有一个失败，那么就跳过执行自己</li>
   *       <li>如果依赖的group有一个跳过，那么就跳过执行自己</li>
   *     </ul>
   *   </li>
   * </ol>
   *
   * group内Validator的执行顺序见：{@link #addValidator(CellValidator)}
   *
   * @param sheet     {@link Sheet}
   * @param sheetMeta {@link SheetMeta}
   * @return true代表校验通过，false代表失败
   */
  boolean validate(Sheet sheet, SheetMeta sheetMeta);

  /**
   *
   * @return 失败消息
   */
  List<Message> getErrorMessages();
}
