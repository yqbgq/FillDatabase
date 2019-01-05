package application;

import properties.CoreProperty;

/**
 * 该抽象类持有了核心配置类，并且增加了Getter以及Setter方法
 * 方便从配置类中读取信息，减少了在TaskClient中看到的和主要逻辑弱相关的代码
 *
 * @author 黄伟
 */
public abstract class AbstractClient {

    //------------Setter and Getter--------------------------
    public static void setUsername(String username){CoreProperty.setUsername(username);}
    public static void setPassword(String password){CoreProperty.setPassword(password);}
    public static void setUrlPrefix(String urlPrefix){CoreProperty.setUrlPrefix(urlPrefix);}
    public static void setUrlSuffix(String urlSuffix){CoreProperty.setUrlSuffix(urlSuffix);}
    public static String getUsername(){return CoreProperty.getUsername();}
    public static String getPassword(){return CoreProperty.getPassword();}
    public static String getUrlPrefix(){return CoreProperty.getUrlPrefix();}
    public static String getUrlSuffix(){return CoreProperty.getUrlSuffix();}
    //------------Setter and Getter--------------------------
}
