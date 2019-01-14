package process;

import task.Task;
import util.FindTask;

import java.sql.*;
import java.util.ArrayList;

public class ForeignAnalyze {
    public static ArrayList<Task> analyze(ArrayList<Task> taskList, Connection con) throws SQLException {
        ArrayList<Task> result = new ArrayList<>();
        ArrayList<Task> temp = new ArrayList<>();
        for(Task task : taskList){
                DatabaseMetaData meta = con.getMetaData();
                ResultSet rs = meta.getImportedKeys(task.getDatabase(),null,task.getTable());
                rs.last();
                if(rs.getRow()==0){
                    result.add(task);
                    continue;
                }
                temp.add(task);
        }
        return ForeignAnalyze.successfulForeignKey(result,temp,con);
    }

    private static ArrayList<String[]> analyzeForeignKey(Task task, Connection con) throws SQLException{
        ArrayList<String[]> refTables = new ArrayList<>();
        DatabaseMetaData meta = con.getMetaData();
        ResultSet rs = meta.getImportedKeys(task.getDatabase(),null,task.getTable());
        while(rs.next()){
            refTables.add(new String[] {rs.getString(1),
                    rs.getString(3)});
        }
        return refTables;
    }

    private static ArrayList<Task> successfulForeignKey(ArrayList<Task> result,
                                                        ArrayList<Task> temp, Connection con) throws SQLException{
        //第一步首先分析每个任务外键指向的表
        for(Task task : temp){
            ArrayList<String[]> refTables = ForeignAnalyze.analyzeForeignKey(task,con);
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
