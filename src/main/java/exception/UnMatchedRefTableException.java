package exception;

/**
 * 当匹配外键指向表出错时抛出该错误
 */
public class UnMatchedRefTableException extends Exception {
    /**
     * 使用父类的构造器构造一个错误类
     */
    public UnMatchedRefTableException() {
        super();
    }

    /**
     * 使用指定的字符串来构造一个错误类
     *
     * @param   s   指定的错误信息
     */
    public UnMatchedRefTableException(String s) {
        super(s);
    }
}
