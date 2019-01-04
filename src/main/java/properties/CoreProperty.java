package properties;

/**
 * 核心配置类，持有和数据库相关的一些配置信息
 *
 * @author 黄伟
 *
 */
public class CoreProperty {
    /**数据库的用户名**/
    private String username;

    /**数据库的密码**/
    private String password;

    /**URL连接的前缀**/
    private String urlPrefix;

    /**URL连接的后缀**/
    private String urlSuffix;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}
