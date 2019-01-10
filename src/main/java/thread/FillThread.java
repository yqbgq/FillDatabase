package thread;

import task.Task;

import java.sql.Connection;

public class FillThread extends AThread implements Runnable{
    FillThread(Task task, Connection con) {
        super(task, con);
    }

    @Override
    public void run() {
        String database = task.getDatabase();
        String table = task.getTable();

    }
}
