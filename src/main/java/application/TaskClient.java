package application;

import task.Task;

import java.util.ArrayList;

public class TaskClient extends AbstractClient{
    /**持有的临时任务列表，确保在构建时较快，在start()方法被调用之后转换成Task对象列表**/
    private ArrayList<String> tempTaskList = new ArrayList<>();

    /**持有的任务列表，在start()方法被调用之后从tempTaskList转化而来，用来保存任务的相关信息**/
    private ArrayList<Task> TaskList = new ArrayList<>();


}
