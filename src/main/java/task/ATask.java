package task;

import types.Type;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务类的抽象类，包含了每个任务的一系列属性以及相对应的Getter 和 Setter
 *
 * @author 黄伟
 */
public abstract class ATask {
    /**任务指向的数据库名**/
    private String database;
    /**任务指向的表名**/
    private String table;
    /**任务需要写入的行数**/
    private AtomicInteger numOfRows;
    /**任务指向的表每一列的属性**/
    private ArrayList<Type> col = new ArrayList<>();

    //----------Getter and Setter----------------
    public String getDatabase() { return database; }
    public void setDatabase(String database) { this.database = database; }
    public String getTable() { return table; }
    public void setTable(String table) { this.table = table; }
    public AtomicInteger getNumOfRows() { return numOfRows; }
    public void setNumOfRows(AtomicInteger numOfRows) { this.numOfRows = numOfRows; }
    public ArrayList<Type> getCol() { return col; }
    public void setCol(ArrayList<Type> col) { this.col = col; }
    //----------Getter and Setter----------------

    public void addField(Type type){this.col.add(type);}
    public int getSize(){return this.col.size();}
    public Type getType(int index){return this.col.get(index);}
    public void reduce(){this.numOfRows.addAndGet(-1);}
}
