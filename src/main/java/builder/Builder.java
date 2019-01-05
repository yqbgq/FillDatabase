package builder;

import application.TaskClient;
import exception.BuildingException;

/**
 * ConcreteBuilder的接口，规定了其中必须实现的一些方法
 *
 * @author 黄伟
 *
 */
public interface Builder {
    TaskClient build() throws BuildingException;

    Builder setUrlPrefix(String urlPrefix);

    Builder setUrlSuffix(String urlSuffix);

    Builder setPassword(String password);

    Builder setUsername(String username);

    Builder addTask(String task, int numOfRows );
}
