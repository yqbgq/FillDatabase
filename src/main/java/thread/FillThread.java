package thread;

import properties.CoreProperty;
import task.Task;
import java.sql.*;


public class FillThread extends AThread {


    public FillThread(Task task, CoreProperty property) {
        super(task, property);
    }

    @Override
    public void run() {
        property.getLogger().log("开始执行任务" + task.getName());
        int numOfSQL = property.getNumOfSQL();      //获取应该插入的行数
        try(Connection conn = DriverManager.getConnection(property.getUrlPrefix()
                + task.getDatabase() + property.getUrlSuffix(),
                property.getUsername(), property.getPassword());
            PreparedStatement ps = conn.prepareStatement(this.property.getSqlProduce().getSQL(this.task, this.property))){
            conn.setAutoCommit(false);
            ps.clearBatch();//设置非自动提交，多条SQL语句一并写入并且手动提交,清除创建对象时存在的SQL语句
            int numOfBatch = 0;
            int count = task.getNumOfRows();
            //该循环将SQL语句写入ps。如果达到提交要求则提交
            //如果还没有达到提交要求但是已经达到总插入数目，也提交
            while(count > 0){
                String sql = this.property.getSqlProduce().getSQL(this.task, this.property);
                ps.addBatch(sql);
                numOfBatch ++;
                if(numOfBatch == numOfSQL || count == 1 ) {
                    try {
                        int[] temp = ps.executeBatch();
                        ps.clearBatch();
                        conn.commit();
                        property.getLogger().log(task.getName() + "完成插入 " + temp.length + " 条记录");
                        numOfBatch = 0;
                    }catch (IndexOutOfBoundsException e){
                        property.getLogger().log(task.getName() +  "存在记录插入失败，插入会继续进行，但是最终插入数目会少于预期");
                        ps.clearBatch();
                        conn.commit();
                    }
                }
                count --;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        property.getLogger().log("完成任务" + task.getName());
    }
}
