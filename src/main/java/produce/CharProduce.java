package produce;

import types.Type;

import java.util.Random;

/**
 * 默认的字符串生成器
 *
 * @author 黄伟
 */

public class CharProduce implements IProduce{
    /**
     * 对于文本型而言，长度可达65535，这个太长了容易导致内存超限。
     * 因此在这里限制为最长为2000
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
        maxLength = maxLength > 2000 ? 2000 : maxLength;
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
