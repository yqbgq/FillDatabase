package produce;

import task.Task;
import types.Int;
import types.Type;

public class SQLProduce {
    private SQLProduce(){}

    public static String getSQL(Task task){
        String database = task.getDatabase();
        String table = task.getTable();
        String prefixSql = "insert into " + database + "." + table + " (";
        String suffixSql = "values(";
        for(Type temp : task.getCol()){
            if(temp.getType().equals("int")){
                if(!((Int)temp).isAutoIncrease()){

                }
            }
        }

        return prefixSql + suffixSql;
    }
}
