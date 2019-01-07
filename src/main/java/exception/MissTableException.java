package exception;

/**
 * 当无法找到指定数据表时，报错
 */
public class MissTableException extends Exception {
    /**
     * 使用父类的构造器构造一个错误类
     */
    public MissTableException() {
        super();
    }

    /**
     * 使用指定的字符串来构造一个错误类
     *
     * @param   s   指定的错误信息
     */
    public MissTableException(String s) {
        super(s);
    }
}
