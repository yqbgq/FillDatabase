package produce;

import org.junit.Test;
import process.TaskAnalyze;
import properties.CoreProperty;
import task.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLProduce {
    @Test
    public void test(){
        Task task = new Task("fill","test3",10);
        String url = "jdbc:mysql://127.0.0.1:3306/fill?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        CoreProperty property = new CoreProperty();
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            task = TaskAnalyze.analyze(task,con);
            System.out.println(task);
            System.out.println(property.getSqlProduce().getSQL(task,property));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
