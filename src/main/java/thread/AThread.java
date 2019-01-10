package thread;

import task.Task;

import java.sql.Connection;

abstract class AThread {
    protected Task task;
    protected Connection con;
    AThread(Task task, Connection con){
        this.task = task;
        this.con = con;
    }
}
