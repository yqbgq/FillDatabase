package util;

import task.Task;

import java.util.ArrayList;

/**
 * 在任务列表中查找符合条件的任务
 *
 * @author 黄伟
 */
public class FindTask {
    public static int find(String database, String table, ArrayList<Task> taskList){
        for(Task task : taskList){
            if(task.getDatabase().equals(database) && task.getTable().equals(table)){
                return taskList.indexOf(task);
            }
        }
        return -1;
    }
}
