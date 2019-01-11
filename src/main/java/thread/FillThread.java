package thread;

import properties.CoreProperty;
import task.Task;

import java.sql.*;

public class FillThread extends AThread {
    FillThread(Task task, CoreProperty property) throws SQLException{
        super(task, property);
        this.conn = DriverManager.getConnection(property.getUrlPrefix()
                        + "fill" + property.getUrlSuffix(),
                property.getUsername(), property.getPassword());
    }

    @Override
    public void run() {
        String sql = this.property.getSqlProduce().getSQL(this.task,this.property);
        System.out.println(sql);
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
