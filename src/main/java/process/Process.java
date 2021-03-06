package process;

import exception.UnMatchedRefTableException;

/**
 * 该接口规定了Client 运行的相关方法
 *
 * @author 黄伟
 */

public interface Process {
    /**
     * 调用工具类TaskAnalyze对临时任务列表中的任务进行分析
     **/
    void analyze();

    /**
     * 开始运行，调用分析方法以及插入方法
     */
    void start();

    /**
     * 开始对数据表进行插入
     *
     * 遍历每一个任务，将任务交给特殊的线程类，并放入线程池中进行运行
     */
    void insert();

    /**
     * 对外键的相关信息进行检查
     */
    void foreignAnalyze() throws UnMatchedRefTableException;
}
