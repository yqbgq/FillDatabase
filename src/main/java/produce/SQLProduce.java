package produce;

import properties.CoreProperty;
import task.Task;
import types.Int;
import types.Type;

/**
 * 默认的SQL生成器
 *
 * @author 黄伟
 */
public class SQLProduce implements ISQLProduce{
    /**
     * 返回按照任务生成的SQL语句
     * @param task 需要进行处理的任务
     * @param property 所需要持有的核心配置类
     * @return 拼接好的SQL字符串
     */
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
                if(temp.getType().equals("char")) {
                    prefixSql = prefixSql + temp.getName();
                    suffixSql = suffixSql + "'" + property.getCharProduce().produce(temp.getLength()) + "'";
                }else{
                    prefixSql = prefixSql + temp.getName();
                    suffixSql = suffixSql + "'" + property.getFloatProduce().produce(temp.getLength()) + "'";
                }
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
