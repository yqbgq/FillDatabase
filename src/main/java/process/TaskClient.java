package process;

import task.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskClient extends AbstractClient implements Process{
    /**设定最大可以开的线程数量，据说这样是最好的**/
    private final int numOfThreads = Runtime.getRuntime().availableProcessors() * 2 + 2;

    /**持有的临时任务列表，确保在构建时较快，在start()方法被调用之后转换成Task对象列表**/
    public ArrayList<Task> tempTaskList = new ArrayList<>();



    /**持有的任务列表，在start()方法被调用之后从tempTaskList转化而来，用来保存任务的相关信息**/
    public ArrayList<Task> taskList = new ArrayList<>();

    @Override
    public Task analyze(Task task) {

        return null;
    }

    @Override
    public void start() {
        Connection con;
        try {
             con= DriverManager.getConnection(getUrlPrefix() + "mysql" + getUrlSuffix(),
                    getUsername(), getPassword());
            for(Task temp : tempTaskList){
                if(temp.getTable() != null){
                    taskList.add(TaskAnalyze.analyze(temp,con));
                }else{
                    ArrayList<Task> x = TaskAnalyze.analyzeDatabase(temp,con);
                    taskList.addAll(x);
                }
            }
            for(Task x : taskList){
                System.out.println(x);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void insert() {

    }
}
