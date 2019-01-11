package produce;

import java.util.Random;

/**
 * 默认的整型字段生成器
 *
 * @author 黄伟
 */
public class IntProduce implements IProduce{
    /**
     * 返回生成的字符串
     * @param maxLength 生成的字符串长度
     * @return  生成的字符串
     */
    @Override
    public  String produce(int maxLength){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while(true){
            int temp = random.nextInt();
            if(sb.length() + String.valueOf(temp).length() > maxLength){
                break;
            }else{
                sb.append(String.valueOf(temp));
            }
        }
        return sb.toString();
    }
}
