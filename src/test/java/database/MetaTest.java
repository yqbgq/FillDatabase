package database;

import org.junit.Test;

import java.sql.*;

public class MetaTest {
    public DatabaseMetaData metaData;

    // 测试获取库中的表
    @Test
    public void getTable(){
        String url = "jdbc:mysql://127.0.0.1:3306/fill?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            metaData = con.getMetaData();
            ResultSet rs = metaData.getTables("fill",null,null,new String[] {"TABLE"});
            while(rs.next()){
                System.out.println(rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //测试获取每一列信息
    @Test
    public void getField(){
        String url = "jdbc:mysql://127.0.0.1:3306/fill?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            DatabaseMetaData m_DBMetaData = con.getMetaData();
            ResultSet colRet = m_DBMetaData.getColumns(null,"%", "test1","%");
            while(colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                String columnType = colRet.getString("TYPE_NAME");
                int datasize = colRet.getInt("COLUMN_SIZE");
                int digits = colRet.getInt("DECIMAL_DIGITS");
                int nullable = colRet.getInt("NULLABLE");
                System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+
                        nullable + colRet.getString("IS_AUTOINCREMENT"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //测试表中的所有原属性
    @Test
    public void getEveryField(){
        String url = "jdbc:mysql://127.0.0.1:3306/fill?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            DatabaseMetaData m_DBMetaData = con.getMetaData();
            ResultSet colRet = m_DBMetaData.getColumns(null,"%", "test1","%");
            while(colRet.next()) {
                System.out.println("====================");
                for(int i=1;i<24;i++){

                    System.out.println(colRet.getString(i));
                }
                System.out.println("====================");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
