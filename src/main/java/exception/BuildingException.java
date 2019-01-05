package exception;

/**
 * 当构造TaskClient出错时，会抛出这个错误
 * 
 * @author 黄伟
 */
public class BuildingException extends Exception {
    /**
     * 使用父类的构造器构造一个错误类
     */
    public BuildingException() {
        super();
    }

    /**
     * 使用指定的字符串来构造一个错误类
     *
     * @param   s   指定的错误信息
     */
    public BuildingException(String s) {
        super(s);
    }
}
