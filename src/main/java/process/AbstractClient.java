package process;

import properties.CoreProperty;

import java.sql.Connection;

/**
 * 该抽象类持有了核心配置类，并且增加了Getter以及Setter方法
 * 方便从配置类中读取信息，减少了在TaskClient中看到的和主要逻辑弱相关的代码
 *
 * @author 黄伟
 */
public abstract class AbstractClient {
    /**持有的核心配置类**/
    private CoreProperty property = new CoreProperty();

    /**持有的数据库连接**/
    private Connection conn ;
    //------------Setter and Getter--------------------------
    public  void setUsername(String username){this.property.setUsername(username);}
    public  void setPassword(String password){this.property.setPassword(password);}
    public  void setUrlPrefix(String urlPrefix){this.property.setUrlPrefix(urlPrefix);}
    public  void setUrlSuffix(String urlSuffix){this.property.setUrlSuffix(urlSuffix);}
    public  String getUsername(){return this.property.getUsername();}
    public  String getPassword(){return this.property.getPassword();}
    public  String getUrlPrefix(){return this.property.getUrlPrefix();}
    public  String getUrlSuffix(){return this.property.getUrlSuffix();}
    public Connection getConn() { return conn; }
    public void setConn(Connection conn) { this.conn = conn; }
    //------------Setter and Getter--------------------------
}
