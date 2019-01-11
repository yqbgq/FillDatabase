package produce;

/**
 * 生成器的接口，所有生成器都必须实现这个接口以及produce方法
 *
 * @author 黄伟
 */
public interface IProduce {
    /**
     * 生成器的生成方法，返回按照自定义规则生成的maxLength长度字符串
     * @param maxLength 约束条件，最大长度
     * @return 生成的字符串
     */
    String produce(int maxLength);
}
