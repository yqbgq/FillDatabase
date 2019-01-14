package process;

import task.Task;
import util.FindTask;
import util.FindType;
import java.sql.*;
import java.util.ArrayList;

/**
 * 这个工具类用于分析任务是否具有外键，如果有的话，则完善外键相关的属性
 *
 * @author 黄伟
 */
class ForeignAnalyze {
    /**
     * 对任务进行外键分析
     * @param taskList 等待分析的任务列表
     * @param con 数据库连接
     * @return 处理完毕的任务
     * @throws SQLException 数据库操作报错
     */
    static ArrayList<Task> analyze(ArrayList<Task> taskList, Connection con) throws SQLException {
        //没有外键的任务列表
        ArrayList<Task> noForeignKey = new ArrayList<>();
        //有外键的任务列表
        ArrayList<Task> temp = new ArrayList<>();
        for(Task task : taskList){
                DatabaseMetaData meta = con.getMetaData();
                ResultSet rs = meta.getImportedKeys(task.getDatabase(),null,task.getTable());
                rs.last();
                //判断是否有外键相关信息，如果有则说明表上有外键
                if(rs.getRow()==0){
                    noForeignKey.add(task);
                    continue;
                }
                temp.add(task);
        }
        return ForeignAnalyze.successfulForeignKey(noForeignKey,temp,con);
    }

    /**
     * 对于每一个具有外键的数据表，检查是哪些字段有外键，分别依赖哪些数据表
     * 并为这些字段对应的类型对象设置相应的属性
     *
     * @param task 需要进行处理判断的任务
     * @param con 数据库连接
     * @return 返回依赖的数据表
     * @throws SQLException 数据库操作错误
     */
    private static ArrayList<String[]> analyzeRefTable(Task task, Connection con) throws SQLException{
        ArrayList<String[]> refTables = new ArrayList<>();
        DatabaseMetaData meta = con.getMetaData();
        ResultSet rs = meta.getImportedKeys(task.getDatabase(),null,task.getTable());
        while(rs.next()){
            //在依赖表中添加依赖的数据表的数据库名和表名
            refTables.add(new String[] {rs.getString(1),
                    rs.getString(3)});
            //----------------为类型对象添加相关的属性--------------------------------------
            task.getCol().get(FindType.find(task.getCol(),rs.getString(4))).setHasForeignKey(true);
            task.getCol().get(FindType.find(task.getCol(),rs.getString(4)))
                    .setForeignKeyDatabase(rs.getString(1));
            task.getCol().get(FindType.find(task.getCol(),rs.getString(4)))
                    .setForeignKeyTable(rs.getString(3));
            task.getCol().get(FindType.find(task.getCol(),rs.getString(4)))
                    .setForeignKeyColumn(rs.getString(4));
            //----------------为类型对象添加相关的属性--------------------------------------
        }
        return refTables;
    }

    /**
     * 对于表上有外键的表，还需要确认在本次应用程序执行中是否能够完成操作
     * 如果一张表上的外键指向一张非任务表，且非任务表没有记录的话，那么就判断该任务是无法完成的
     * @param result 表上无外键的任务表
     * @param temp 等待判断的数据表
     * @param con 数据库连接
     * @return 可以成功完成的数据表集合
     * @throws SQLException 数据库操作失败
     */
    private static ArrayList<Task> successfulForeignKey(ArrayList<Task> result,
                                                        ArrayList<Task> temp, Connection con) throws SQLException{
        //第一步首先分析每个任务外键指向的表
        for(Task task : temp){
            ArrayList<String[]> refTables = ForeignAnalyze.analyzeRefTable(task,con);
            task.setRefTables(refTables);
        }
        //如果指向的表都在result列表中，则任务是可以完成的
        for(Task task : temp){
            for(String[] table : task.getRefTables()){
                if(FindTask.find(table[0],table[1],result) == -1){
                    break;
                }
            }
            result.add(task);
        }
        temp.removeAll(result);
        //如果所需求的表不再任务重，但是有记录，那么任务也是可以完成的
        //最后查遗补漏,当因为条件改变，没有再出现满足条件的任务时，跳出
        while(true){
            int count = 0 ;
            for(Task task : temp){
                for(String[] table : task.getRefTables()){
                    if(FindTask.find(table[0],table[1],result) == -1){
                        if(! ForeignAnalyze.exists(table,con)){
                            break;
                        }
                    }
                }
                result.add(task);
                count ++;
            }
            if(count ==0){
                break;
            }
            temp.removeAll(result);
        }
        return result;
    }

    /**
     * 判断任务表所依赖的非任务表是否有记录
     *
     * @param table 依赖的表名
     * @param con 数据库连接
     * @return 布尔类型，是否有记录的判断
     * @throws SQLException 数据库操作出错
     */
    private static boolean exists(String[] table, Connection con) throws SQLException{
        String sql = "select count(*) as count from " + table[0] +"."+table[1];
        Statement statement  = con.createStatement();
        statement.execute(sql);
        ResultSet rs = statement.getResultSet();
        rs.next();
        System.out.println(rs.getInt("count"));
        return rs.getInt("count") > 0 ;
    }
}
