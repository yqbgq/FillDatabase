package thread;

import org.junit.Test;
import process.TaskAnalyze;
import properties.CoreProperty;
import task.Task;

import java.sql.Connection;
import java.sql.DriverManager;

public class testAddThread {

    public static void main(String[] args) throws Exception{
        testAddThread t = new testAddThread();
        t.test();
    }

    public void test() throws Exception{
        Task task = new Task("fill","test1",100);
        CoreProperty property = new CoreProperty();
        property.setPassword("root");
        property.setUsername("root");
        Connection conn = DriverManager.getConnection(property.getUrlPrefix()
                        + "fill" + property.getUrlSuffix(),
                property.getUsername(), property.getPassword());
        task = TaskAnalyze.analyze(task,conn);
        FillThread f = new FillThread(task,property);
        System.out.println(task);
        f.start();
    }
}
