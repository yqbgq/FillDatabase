package thread;

import properties.CoreProperty;
import task.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class AThread extends Thread{
    protected Task task;
    protected CoreProperty property;
    protected Connection conn;
    AThread(Task task, CoreProperty property){
        this.task = task;
        this.property = property;
    }
}
