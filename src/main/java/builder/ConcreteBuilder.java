package builder;

import application.TaskClient;
import exception.BuildingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 黄伟
 */
public class ConcreteBuilder implements Builder {
    private TaskClient client = new TaskClient();
    /**
     * 使用此方法对之前完成的构建方法进行校验，确认必要信息已经填写
     * 如果校验通过则返回经过构建的TaskClient的对象
     * 否则抛出错误
     *
     * @return 返回经过构建的TaskClient的对象
     */
    public TaskClient build() throws BuildingException{
        if(this.client.getPassword() == null && this.client.getUsername() == null){
            throw new BuildingException("请检查是否已经输入了数据库的用户名和密码！");
        }
        try {
            Connection conn = DriverManager.getConnection(client.getUrlPrefix() + "mysql" + client.getUrlSuffix()
                    , client.getUsername(), client.getPassword());
            if(! conn.isValid(2)){
                throw new BuildingException("在构造时会测试连接MySQL系统的数据库mysql检测用户名和密码，连接失败！");
            }
        }catch (SQLException e){
            throw new BuildingException("在构造时会测试连接MySQL系统的数据库mysql检测用户名和密码，连接失败！");
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

    @Override
    public Builder addTask(String task, int numOfRows){
        client.tempTaskList.put(task,numOfRows);
        return this;
    }
}
