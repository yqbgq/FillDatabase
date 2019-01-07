package process;

import exception.MissTableException;
import task.Task;
import types.Char;
import types.Int;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 分析每个任务，如果指定了表名，则构建字段信息
 *
 * 如果指定了库名，则拆分进行构建
 *
 * @author 黄伟
 *
 */
public class TaskAnalyze {
    /**
     * 静态方法，作为类的工具方法，用来分析单个指定了表名的任务
     *
     * @param task 指定了表名的任务
     * @param conn 数据库链接
     * @return 构建完成的任务
     * @throws Exception 如果数据表不存在或者出现SQLException则抛出错误
     */
    public static Task analyze(Task task, Connection conn) throws Exception {
        DatabaseMetaData metaData = conn.getMetaData();//获取数据库的元数据
        //获取指定数据表的元数据
        ResultSet colRet = metaData.getColumns(task.getDatabase(), "%", task.getTable(),"%");
        while(colRet.next()) {
            String columnName = colRet.getString("COLUMN_NAME");
            String columnType = colRet.getString("TYPE_NAME");
            int size = colRet.getInt("COLUMN_SIZE");
            int nullable = colRet.getInt("NULLABLE");
            boolean autoincrement = colRet.getString("IS_AUTOINCREMENT").equalsIgnoreCase("YES");
            if(columnType.toLowerCase().contains("int")){
                Int type= new Int(columnName,size, nullable == 1,autoincrement);
                task.addField(type);
            }else{
                if(columnType.toLowerCase().contains("char")){
                    Char type = new Char(columnName,size, nullable == 1);
                    task.addField(type);
                }
            }
        }
        if(task.getSize() == 0){
            throw new MissTableException("数据库中不存在指定数据表！");
        }else {
            return task;
        }
    }

    public static ArrayList<Task> analyzeDatabase(Task task, Connection con){
        ArrayList<Task> result = new ArrayList<>();
        try {
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet rs = metaData.getTables("java", null, null, new String[]{"TABLE"});
            ArrayList<String> tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString(3));
            }
            for(String x : tables){
                Task temp = new Task(task.getDatabase(),x,task.getNumOfRows().get());
                try {
                    result.add(analyze(temp, con));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
