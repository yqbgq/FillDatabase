package produce;

import org.junit.Test;
import process.TaskAnalyze;
import task.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLProduce {
    @Test
    public void test(){
        Task task = new Task("fill","test1",10);
        String url = "jdbc:mysql://127.0.0.1:3306/fill?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            task = TaskAnalyze.analyze(task,con);
            //System.out.println(SQLProduce.getSQL(task));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
