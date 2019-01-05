package properties;

/**
 * 核心配置类，持有和数据库相关的一些配置信息
 *
 * @author 黄伟
 *
 */
public class CoreProperty {
    /**数据库的用户名**/
    private  String username;

    /**数据库的密码**/
    private  String password;

    /**URL连接的前缀**/
    private  String urlPrefix = "jdbc:mysql://127.0.0.1:3306/";

    /**URL连接的后缀**/
    private  String urlSuffix = "?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

    public  String getUsername() {
        return this.username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  String getUrlPrefix() {
        return this.urlPrefix;
    }

    public  void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public  String getUrlSuffix() {
        return this.urlSuffix;
    }

    public  void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}
