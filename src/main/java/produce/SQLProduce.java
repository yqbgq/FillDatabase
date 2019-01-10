package produce;

import task.Task;
import types.Type;

public class SQLProduce {
    private SQLProduce(){}

    public static String getSQL(Task task){
        String database = task.getDatabase();
        String table = task.getTable();
        String sql = "insert into " + database + "." + "table" + "(";
        for(Type temp : task.getCol()){

        }

        return sql;
    }
}
