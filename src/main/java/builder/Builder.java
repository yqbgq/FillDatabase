package builder;

import application.TaskClient;

/**
 * ConcreteBuilder的接口，规定了其中必须实现的一些方法
 *
 * @author 黄伟
 *
 */
public interface Builder {
    TaskClient build();

    Builder setUrlPrefix(String urlPrefix);

    Builder setUrlSuffix(String urlSuffix);

    Builder setPassword(String password);

    Builder setUsername(String username);
}
