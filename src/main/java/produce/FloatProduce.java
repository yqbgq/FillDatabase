package produce;

import types.Type;

import java.util.Random;

//TODO 判断非空与否能否创建是空的，而且这个好像判断长度有些不准确
public class FloatProduce implements IProduce {
    @Override
    public String produce(Type type) {
        if(type.isHasForeignKey()){
            return "(select " + type.getForeignKeyColumn() +
                    " from " + type.getForeignKeyDatabase() + "."+
                    type.getForeignKeyTable() + " order by rand() limit 1)";
        }
        Random random = new Random();
        return "'" + String.valueOf(random.nextFloat()) +"'";
    }
}
