package produce;

import properties.CoreProperty;
import task.Task;

/**
 * SQL生成器接口，SQL生成器必须实现这个接口
 *
 * @author 黄伟
 */
public interface ISQLProduce {
    String getSQL(Task task, CoreProperty property);
}
