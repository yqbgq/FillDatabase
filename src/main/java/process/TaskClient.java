package process;

import exception.UnMatchedRefTableException;
import task.Task;
import thread.FillThread;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        this.getProperty().getLogger().log("开始对任务列表进行分析");
        this.analyze();
        this.getProperty().getLogger().log("任务分析完成，检查任务表上是否存在外键");
        try{
            this.foreignAnalyze();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.getProperty().getLogger().log("外键检查完成，即将开始执行任务");
        this.insert();
    }

    @Override
    public void insert() {
        ExecutorService exe = Executors.newFixedThreadPool(property.getNumOfThreads());
        ArrayList<FillThread> threads = new ArrayList<>(taskList.size());
        ArrayList<Task> foreignTaskList = new ArrayList<>();
        for(Task task : taskList){
            if(task.getRefTables()==null) {
                threads.add(new FillThread(task, property));
            }else{
                foreignTaskList.add(task);
            }
        }
        taskList.clear();
        for(Thread t : threads){
            exe.execute(t);
        }
        exe.shutdown();
        try {
            exe.awaitTermination(1, TimeUnit.HOURS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for(Task task : foreignTaskList){
            Thread t = new FillThread(task, property);
            t.start();
            try {
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        taskList.clear();
    }

    @Override
    public void foreignAnalyze() throws UnMatchedRefTableException {
        int count = this.taskList.size();
        try {
            Connection conn = DriverManager.getConnection(property.getUrlPrefix()
                            + "mysql" + property.getUrlSuffix(),
                    property.getUsername(), property.getPassword());
            taskList = ForeignAnalyze.analyze(taskList, conn);
            if (taskList.size() != count) {
                throw new UnMatchedRefTableException("匹配外键信息时出错，请检查任务是否满足外键限制！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 允许后续添加任务
     * @param database 数据库名
     * @param table 数据表名
     * @param numOfRows 需要写入的记录行数
     */
    public void addTask(String database , String table, int numOfRows){
        Task task = new Task(database,table,numOfRows);
        this.tempTaskList.add(task);
    }

    /**
     * 允许在加入任务之后删除任务
     * @param index 需要删除的任务的编号
     */

    public void removeTask(int index){
        this.tempTaskList.remove(index);
    }
}
