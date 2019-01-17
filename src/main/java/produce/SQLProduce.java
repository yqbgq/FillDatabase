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
        //构造SQL语句，判断当前列的类型，并交由对应的生成器生成字符串
        for(Type temp : task.getCol()){
            if(temp.getType().contains("int")){
                //如果是自增的，则不进行处理
                if(!((Int)temp).isAutoIncrease()){
                    prefixSql.append(  temp.getName() );
                    suffixSql.append(  property.getIntProduce().produce(temp) );
                }
            }else{
                if(temp.getType().contains("char")) {
                    prefixSql.append(temp.getName());
                    suffixSql.append(  property.getCharProduce().produce(temp) );
                }else{
                    if(temp.getType().contains("float")) {
                        prefixSql.append(temp.getName());
                        suffixSql.append(  property.getFloatProduce().produce(temp) );
                    }else{
                        if(temp.getType().contains("date")) {
                            prefixSql.append(temp.getName());
                            suffixSql.append(property.getDateProduce().produce(temp));
                        }else{
                            if(temp.getType().contains("enum")){
                                prefixSql.append(temp.getName());
                                suffixSql.append(property.getEnumProduce().produce(temp));
                            }else{
                                if(temp.getType().contains("bit")){
                                    prefixSql.append(temp.getName());
                                    suffixSql.append(property.getBitProduce().produce(temp));
                                }
                            }

                        }
                    }
                }
            }
            //当不是最后一列时需要添加逗号
            if(task.getCol().indexOf(temp) != task.getCol().size()-1  ){
                //当不是整型和自增时，添加逗号
                if(!(temp.getType().contains("int") && ((Int)temp).isAutoIncrease())){
                    prefixSql.append(",");
                    suffixSql.append(",");
                }
            }
        }
        prefixSql.append(")");
        suffixSql.append( ")");
        return prefixSql.append(" ").append(suffixSql).toString();
    }
}
