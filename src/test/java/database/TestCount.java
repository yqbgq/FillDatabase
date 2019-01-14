package database;

import org.junit.Test;
import properties.CoreProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestCount {
    @Test
    public void test() throws Exception{
        String sql = "select count(*) as count from fill.test1 ";
        CoreProperty property = new CoreProperty();
        property.setUsername("root");
        property.setPassword("root");
        Connection conn = DriverManager.getConnection(property.getUrlPrefix()
                        + "mysql" + property.getUrlSuffix(),
                property.getUsername(), property.getPassword());
        Statement statement  = conn.createStatement();
        statement.execute(sql);
        ResultSet rs = statement.getResultSet();
        rs.next();
        System.out.println(rs.getInt("count"));
    }
}
