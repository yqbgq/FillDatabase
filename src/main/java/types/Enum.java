package types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 枚举类型，它的构造方法和一般的类型相差较大
 *
 * @author 黄伟
 *
 */
public class Enum extends Type{
    /**
     * Enum类的构造方法，因为在MySQL中它有些特殊，所以采用和其他类型构造方法比较有差异的方法
     * 最大的差别就是它需要再使用一种方法查找表的元数据，获取枚举中有多少个候选项
     *
     * @param name 列名
     * @param allowEmpty 是否允许为空
     * @param con 数据库连接
     * @param database 数据库名
     * @param table 数据表名
     */
    public Enum(String name,  boolean allowEmpty, Connection con,String database,String table){
        this.setName(name);
        this.setAllowEmpty(allowEmpty);
        this.setType("enum");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SHOW COLUMNS FROM  " + database +"." + table);
            while(rs.next() ) {
                if(rs.getString("field").equals(name)) {
                    String enums = rs.getString("Type");
                    this.setLength(enums.split("',").length);
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
