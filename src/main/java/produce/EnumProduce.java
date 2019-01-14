package produce;

import types.Type;

import java.util.Random;

/**
 * 枚举类的默认生成器
 *
 * @author 黄伟
 */
public class EnumProduce implements IProduce{
    /**
     * 返回随机选中的枚举类中的候选项索引
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
        Random random = new Random();
        int index ;
        //如果为0的话，不能正确选中候选项，必须从1开始
        do{
            index = random.nextInt(maxLength);
        }while(index==0);
        return "'" + String.valueOf(index) + "'";
    }
}
