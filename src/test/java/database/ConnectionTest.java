package database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void getConnection(){
        String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println(con.isValid(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
