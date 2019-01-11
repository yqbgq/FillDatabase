package produce;

import properties.CoreProperty;
import task.Task;
import types.Int;
import types.Type;

public class SQLProduce implements ISQLProduce{
    @Override
    public  String getSQL(Task task, CoreProperty property){
        String database = task.getDatabase();
        String table = task.getTable();
        String prefixSql = "insert into " + database + "." + table + "(";
        String suffixSql = "values(";
        for(Type temp : task.getCol()){
            if(temp.getType().equals("int")){
                if(!((Int)temp).isAutoIncrease()){
                    prefixSql = prefixSql +  temp.getName() ;
                    suffixSql = suffixSql + "'" + property.getIntProduce().produce(temp.getLength()) + "'";
                }
            }else{
                prefixSql = prefixSql +  temp.getName() ;
                suffixSql = suffixSql + "'" +  property.getCharProduce().produce(temp.getLength())  + "'";

            }
            if(task.getCol().indexOf(temp) != task.getCol().size()-1){
                prefixSql += ",";
                suffixSql += ",";
            }
        }
        prefixSql += ")";
        suffixSql += ")";

        return prefixSql + " " + suffixSql;
    }
}
