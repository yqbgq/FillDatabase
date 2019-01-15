package thread;

import properties.CoreProperty;
import task.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 线程的抽象类
 *
 * @author 黄伟
 */
abstract class AThread extends Thread{
    protected Task task;
    protected CoreProperty property;
    AThread(Task task, CoreProperty property){
        this.task = task;
        this.property = property;
        this.setName(task.getDatabase()+"."+task.getTable());
    }
}
