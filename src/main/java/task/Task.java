package task;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务抽象类ATask的实现类，记录了具体任务的相关信息
 *
 * @author 黄伟
 */
public class Task extends ATask{
    public Task(String database, String table ,int numOfRows){
        this.setDatabase(database);
        this.setTable(table);
        this.setNumOfRows(new AtomicInteger(numOfRows));
    }
}
