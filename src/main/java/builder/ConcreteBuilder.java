package builder;

import application.TaskClient;

/**
 * @author 黄伟
 */
public class ConcreteBuilder implements Builder {
    private static TaskClient client = new TaskClient();

    //不允许使用通过new进行初始化
    private ConcreteBuilder(){}

    /**
     * 使用此方法对之前完成的构建方法进行校验，确认必要信息已经填写
     * 如果校验通过则返回经过构建的TaskClient的对象
     * 否则抛出错误
     *
     * @return 返回经过构建的TaskClient的对象
     */
    private static TaskClient builder(){
        
        return client;
    }
}
