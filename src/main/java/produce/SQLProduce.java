package produce;

import properties.CoreProperty;
import task.Task;
import types.Int;
import types.Type;
//TODO 抽取重复代码
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
        StringBuilder prefixSql = new StringBuilder("insert into " + database + "." + table + "(");
        StringBuilder suffixSql = new StringBuilder("values(");
        for(Type temp : task.getCol()){
            if(temp.getType().equals("int")){
                if(!((Int)temp).isAutoIncrease()){
                    prefixSql.append(  temp.getName() );
                    suffixSql.append(  property.getIntProduce().produce(temp) );
                }
            }else{
                if(temp.getType().equals("char")) {
                    prefixSql.append(temp.getName());
                    suffixSql.append(  property.getCharProduce().produce(temp) );
                }else{
                    if(temp.getType().equals("float")) {
                        prefixSql.append(temp.getName());
                        suffixSql.append(  property.getFloatProduce().produce(temp) );
                    }else{
                        if(temp.getType().equals("date")) {
                            prefixSql.append(temp.getName());
                            suffixSql.append(property.getDateProduce().produce(temp));
                        }else{
                            prefixSql.append(temp.getName());
                            suffixSql.append(property.getEnumProduce().produce(temp));
                        }
                    }
                }
            }

            if(task.getCol().indexOf(temp) != task.getCol().size()-1){
                prefixSql.append(",");
                suffixSql.append( ",");
            }
        }

        prefixSql.append(")");
        suffixSql.append( ")");

        String result = prefixSql.append(" ").append(suffixSql).toString();
        //System.out.println(result);
        return result;
    }
}
