package database;

import org.junit.Test;
import properties.CoreProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestInsert {
    @Test
    public void test() throws Exception{
        String sql = "insert into fill.test1(id,name) values('-884089661','fjaqgurp')";
        CoreProperty property = new CoreProperty();
        property.setUsername("root");
        property.setPassword("root");
        Connection conn = DriverManager.getConnection(property.getUrlPrefix()
                        + "fill" + property.getUrlSuffix(),
                property.getUsername(), property.getPassword());
        Statement statement  = conn.createStatement();
        statement.execute(sql);
    }
}
