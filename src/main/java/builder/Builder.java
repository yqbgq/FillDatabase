package builder;

import process.TaskClient;
import exception.BuildingException;

/**
 * ConcreteBuilder的接口，规定了其中必须实现的一些方法
 *
 * @author 黄伟
 *
 */
public interface Builder {
    /**
     * 构建出TaskClient对象
     * @return 构建完成的TaskClient对象
     * @throws BuildingException 如果构建失败，则抛出此错误
     */
    TaskClient build() throws BuildingException;

    /**
     * 在构造中设定数据库连接的前缀URL
     * @param urlPrefix 数据库连接的前缀URL
     * @return 正在构建中的Builder
     */
    Builder setUrlPrefix(String urlPrefix);

    /**
     * 在构造中设定数据库连接的后缀URL
     * @param urlSuffix 数据库连接的后缀URL
     * @return 正在构建中的Builder
     */
    Builder setUrlSuffix(String urlSuffix);

    /**
     * 在构造中设定数据库连接的后密码
     * @param password 数据库连接密码
     * @return 构造中的Builder
     */
    Builder setPassword(String password);

    /**
     * 在构造中设定数据库连接的用户名
     * @param username 数据库连接的用户名
     * @return 构造中的Builder
     */
    Builder setUsername(String username);

    /**
     * 在构造中添加将来需要完成处理的任务
     * @param database 数据库库名
     * @param table 数据表表明
     * @param numOfRows 希望插入的行数
     * @return 构造中的Builder
     */
    Builder addTask(String database , String table, int numOfRows);

    /**
     * 设置配置类中属性numOfSQL的值，默认为40
     * @param num 想要修改的值
     * @return 构造中的Builder
     */
    Builder setNumOfSQL(int num);
}
