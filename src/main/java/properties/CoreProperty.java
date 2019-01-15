package properties;

import Logger.ILogger;
import Logger.Logger;
import produce.*;

/**
 * 核心配置类，持有和程序行为相关的一些配置信息
 * 相关的属性有：
 * 1. 数据库的用户名、密码
 * 2. 数据库连接的前缀和后缀
 * 3. 最大的线程数
 * 4. 各种类型字段默认的生成器
 * 5. 默认的SQL生成器
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

    /**默认的整型生成器**/
    private IProduce intProduce = new IntProduce();

    /**默认的字符串生成器**/
    private IProduce charProduce = new CharProduce();

    /**默认的浮点生成器**/
    private IProduce floatProduce = new FloatProduce();

    /**默认的日期生成器**/
    private IProduce DateProduce = new DateProduce();

    /**默认的枚举生成器**/
    private IProduce enumProduce = new EnumProduce();

    /**默认的SQL生成器**/
    private ISQLProduce sqlProduce = new SQLProduce();

    /** 插入线程执行时，每个PreparedStatement携带的SQL语句数量**/
    private int numOfSQL = 10000;

    /**默认的日志器**/
    private ILogger logger = new Logger();
    //------------------Setter and Getter ----------------------------------------
    public  String getUsername() { return this.username; }
    public  void setUsername(String username) { this.username = username; }
    public  String getPassword() { return password; }
    public  void setPassword(String password) { this.password = password; }
    public  String getUrlPrefix() { return this.urlPrefix; }
    public  void setUrlPrefix(String urlPrefix) { this.urlPrefix = urlPrefix; }
    public  String getUrlSuffix() { return this.urlSuffix; }
    public  void setUrlSuffix(String urlSuffix) { this.urlSuffix = urlSuffix; }
    public int getNumOfThreads() { return numOfThreads; }
    public void setNumOfThreads(int numOfThreads) { this.numOfThreads = numOfThreads; }
    public IProduce getIntProduce() { return intProduce; }
    public void setIntProduce(IProduce intProduce) { this.intProduce = intProduce; }
    public IProduce getCharProduce() { return charProduce; }
    public void setCharProduce(IProduce charProduce) { this.charProduce = charProduce; }
    public ISQLProduce getSqlProduce() { return sqlProduce; }
    public void setSqlProduce(ISQLProduce sqlProduce) { this.sqlProduce = sqlProduce; }
    public IProduce getFloatProduce() { return floatProduce; }
    public void setFloatProduce(IProduce floatProduce) { this.floatProduce = floatProduce; }
    public IProduce getDateProduce() { return DateProduce; }
    public void setDateProduce(IProduce dateProduce) { DateProduce = dateProduce; }
    public int getNumOfSQL() {return numOfSQL; }
    public void setNumOfSQL(int numOfSQL) { this.numOfSQL = numOfSQL; }
    public IProduce getEnumProduce() { return enumProduce; }
    public void setEnumProduce(IProduce enumProduce) { this.enumProduce = enumProduce; }
    public ILogger getLogger() { return logger; }
    public void setLogger(ILogger logger) { this.logger = logger; }
    //------------------Setter and Getter ----------------------------------------
}
