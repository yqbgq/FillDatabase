package database;

import org.junit.Test;

import java.sql.*;

public class MetaTest {
    public DatabaseMetaData metaData;

    // 测试获取库中的表
    @Test
    public void getTable(){
        String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            metaData = con.getMetaData();
            ResultSet rs = metaData.getTables("java",null,null,new String[] {"TABLE"});
            while(rs.next()){
                System.out.println(rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
