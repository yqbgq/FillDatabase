package task;

import types.Type;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务抽象类ATask的实现类，记录了具体任务的相关信息
 *
 * @author 黄伟
 */
public class Task extends ATask implements Cloneable{
    public Task(String database, String table ,int numOfRows){
        this.setDatabase(database);
        this.setTable(table);
        this.setNumOfRows(new AtomicInteger(numOfRows));
    }

    public Object clone(){
        Object obj = null;
        try{
            obj = super.clone();
        }catch(CloneNotSupportedException ex){
            ex.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("该任务指定数据库为" + getDatabase() + "\n");
        sb.append("该任务指定数据表为" + getTable() + "\n");
        for(int i=0;i<getSize();i++){
            Type temp = getType(i);
            sb.append("列名" + temp.getName() +"\n");
        }
        return sb.toString();
    }
}
