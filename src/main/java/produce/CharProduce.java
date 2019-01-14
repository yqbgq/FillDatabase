package produce;

import types.Type;

import java.util.Random;

/**
 * 默认的字符串生成器
 *
 * @author 黄伟
 */

//TODO 判断非空与否能否创建是空的，而且这个好像判断长度有些不准确
public class CharProduce implements IProduce{
    /**
     * 返回生成的字符串
     * @return 构造完成的字符串
     */
    @Override
    public String produce(Type type){
        if(type.isHasForeignKey()){
            return "(select " + type.getForeignKeyColumn() +
                    " from " + type.getForeignKeyDatabase() + "."+
                    type.getForeignKeyTable() + " order by rand() limit 1)";
        }
        int maxLength = type.getLength();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int count = random.nextInt(maxLength);
        sb.append("'");
        sb.append((char)('a' + random.nextInt(24)));
        for(int i=0;i<count;i++){
            sb.append((char)('a' + random.nextInt(24)));
        }
        sb.append("'");
        return sb.toString();
    }


}
