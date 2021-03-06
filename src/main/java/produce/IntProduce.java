package produce;

import types.Type;

import java.util.Random;
//TODO 判断非空与否能否创建是空的，而且这个好像判断长度有些不准确
/**
 * 默认的整型字段生成器
 *
 * @author 黄伟
 */
public class IntProduce implements IProduce{
    /**
     * 返回生成的字符串
     * @return  生成的字符串
     */
    @Override
    public  String produce(Type type){
        if(type.isHasForeignKey()){
            return "(select " + type.getForeignKeyColumn() +
                    " from " + type.getForeignKeyDatabase() + "."+
                    type.getForeignKeyTable() + " order by rand() limit 1)";
        }
        int maxLength = type.getLength();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        int length = random.nextInt(maxLength);
        length = length == 0 ? length + 1 : length;
        for(int i =0 ;i<length;i++){
            sb.append(random.nextInt(10));
        }
        sb.append("'");
        return sb.toString();
    }
}
