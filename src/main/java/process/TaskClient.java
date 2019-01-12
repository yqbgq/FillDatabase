package process;

import task.Task;
import thread.FillThread;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskClient extends AbstractClient implements Process{
    /**持有的临时任务列表，确保在构建时较快，在start()方法被调用之后转换成Task对象列表**/
    public ArrayList<Task> tempTaskList = new ArrayList<>();

    /**持有的任务列表，在start()方法被调用之后从tempTaskList转化而来，用来保存任务的相关信息**/
    private ArrayList<Task> taskList = new ArrayList<>();

    @Override
    public void analyze() {
        try(Connection con= DriverManager.getConnection(getUrlPrefix() + "mysql" + getUrlSuffix(),
                getUsername(), getPassword())){
            for(Task temp : tempTaskList){
                if(temp.getTable() != null){
                    taskList.add(TaskAnalyze.analyze(temp,con));
                }else{
                    ArrayList<Task> x = TaskAnalyze.analyzeDatabase(temp,con);
                    taskList.addAll(x);
                }
            }
            tempTaskList.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        this.analyze();
        this.insert();
    }

    @Override
    public void insert() {
        ExecutorService exe = Executors.newFixedThreadPool(property.getNumOfThreads());
        ArrayList<FillThread> threads = new ArrayList<>(taskList.size());
        for(Task task : taskList){
            threads.add(new FillThread(task,property));
        }
        for(Thread t : threads){
            exe.execute(t);
        }
        exe.shutdown();

    }
}
