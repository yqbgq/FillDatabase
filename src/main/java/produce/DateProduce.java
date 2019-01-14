package produce;

import types.Type;

import java.util.Random;

public class DateProduce implements IProduce {
    /**
     * 返回生成的字符串
     * @return 生成的日期字符串
     */
    @Override
    public String produce(Type type){
        if(type.isHasForeignKey()){
            return "(select " + type.getForeignKeyColumn() +
                    " from " + type.getForeignKeyDatabase() + "."+
                    type.getForeignKeyTable() + " order by rand() limit 1)";
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int year = random.nextInt(9999);
        int month = random.nextInt(12);
        int day;
        if(month==2){
            day = random.nextInt(28);
        }else{
            day = random.nextInt(30);
        }
        sb.append("'");
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        sb.append("'");
        return sb.toString();
    }
}
