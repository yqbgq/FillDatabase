package produce;

import properties.CoreProperty;
import task.Task;

public interface ISQLProduce {
    String getSQL(Task task, CoreProperty property);
}
