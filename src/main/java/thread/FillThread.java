package thread;

import properties.CoreProperty;
import task.Task;

import java.sql.*;

public class FillThread extends AThread {
    public FillThread(Task task, CoreProperty property) {
        super(task, property);
        try {
            this.conn = DriverManager.getConnection(property.getUrlPrefix()
                            + "fill" + property.getUrlSuffix(),
                    property.getUsername(), property.getPassword());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(task.reduce() >= 0) {
            String sql = this.property.getSqlProduce().getSQL(this.task, this.property);
            try {
                Statement stmt = this.conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
