package properties;

/**
 * 核心配置类，持有和数据库相关的一些配置信息
 *
 * @author 黄伟
 *
 */
public class CoreProperty {
    /**数据库的用户名**/
    private static String username;

    /**数据库的密码**/
    private static String password;

    /**URL连接的前缀**/
    private static String urlPrefix;

    /**URL连接的后缀**/
    private static String urlSuffix;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CoreProperty.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CoreProperty.password = password;
    }

    public static String getUrlPrefix() {
        return urlPrefix;
    }

    public static void setUrlPrefix(String urlPrefix) {
        CoreProperty.urlPrefix = urlPrefix;
    }

    public static String getUrlSuffix() {
        return urlSuffix;
    }

    public static void setUrlSuffix(String urlSuffix) {
        CoreProperty.urlSuffix = urlSuffix;
    }
}
