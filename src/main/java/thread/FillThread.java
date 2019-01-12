package thread;

import properties.CoreProperty;
import task.Task;

import java.sql.*;

public class FillThread extends AThread {
    final int NUMOFBATCH = 40;

    public FillThread(Task task, CoreProperty property) {
        super(task, property);
    }

    @Override
    public void run() {
        System.out.println("开始任务"+task.getDatabase()+"."+task.getTable());
        try(Connection conn = DriverManager.getConnection(property.getUrlPrefix()
                + "fill" + property.getUrlSuffix(),
                property.getUsername(), property.getPassword());
            PreparedStatement ps = conn.prepareStatement(this.property.getSqlProduce().getSQL(this.task, this.property))){
            conn.setAutoCommit(false);
            ps.clearBatch();
            int numOfBatch = 0;
            int count = task.reduce();
            while(count >= 0) {
                String sql = this.property.getSqlProduce().getSQL(this.task, this.property);
                if(count==0){
                    ps.addBatch(sql);
                    ps.executeBatch();
                    conn.commit();
                }else{
                    if(numOfBatch < NUMOFBATCH){
                        numOfBatch ++;
                        ps.addBatch(sql);
                    }else{
                        ps.addBatch(sql);
                        numOfBatch = 0;
                        ps.executeBatch();
                        ps.clearBatch();
                        conn.commit();
                    }
                }
                count = task.reduce();

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("结束任务"+task.getDatabase()+"."+task.getTable());
    }
}
