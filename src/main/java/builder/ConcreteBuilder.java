package builder;

import application.TaskClient;

/**
 * @author 黄伟
 */
public class ConcreteBuilder implements Builder {
    private TaskClient client = new TaskClient();

    //不允许使用通过new进行初始化
    private ConcreteBuilder(){}

    /**
     * 使用此方法对之前完成的构建方法进行校验，确认必要信息已经填写
     * 如果校验通过则返回经过构建的TaskClient的对象
     * 否则抛出错误
     *
     * @return 返回经过构建的TaskClient的对象
     */
    private  TaskClient builder(){
        if(this.client.getPassword() == null && this.client.getUsername() == null){
            System.out.println("构建错误！");
        }
        return client;
    }

    @Override
    public Builder setUsername(String username){
        this.client.setUsername(username);
        return this;
    }

    @Override
    public Builder setPassword(String password){
        this.client.setPassword(password);
        return this;
    }

    @Override
    public Builder setUrlPrefix(String urlPrefix){
        this.client.setUrlPrefix(urlPrefix);
        return this;
    }

    @Override
    public Builder setUrlSuffix(String urlSuffix){
        this.client.setUrlSuffix(urlSuffix);
        return this;
    }
}
