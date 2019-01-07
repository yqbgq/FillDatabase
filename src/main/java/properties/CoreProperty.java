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

    /**URL连接的后缀，后面的这些属性是为了让MySQL驱动不报错，所以必须设置使用SSL，设置时区以及编码**/
    private  String urlSuffix = "?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

    /**设定最大可以开的线程数量，据说这样是最好的**/
    private int numOfThreads = Runtime.getRuntime().availableProcessors() * 2 + 2;

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

    public int getNumOfThreads() { return numOfThreads; }

    public void setNumOfThreads(int numOfThreads) { this.numOfThreads = numOfThreads; }
}
