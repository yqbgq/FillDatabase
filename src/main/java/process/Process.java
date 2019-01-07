package process;

import task.Task;

public interface Process {
    /**对传入的临时任务的分析，存在三种种情况：
     *      1. 传入的是标准的   数据库.表名
     *      2. 传入的是         数据库.
     *      3. 传入的是         .表名
     *
     * 最后还应该获取表中每一列的信息
     *
     * @param task  由用户传入的任务类
     * @return 返回被拆分完成的任务
     */
    Task analyze(Task task);

    /**
     * 开启所有行为，包括对任务的分析
     */
    void start();

    /**
     * 开始对数据库进行插入
     *
     * 遍历每一个任务，将任务交给特殊的线程类，并放入线程池中进行运行
     */
    void insert();
}