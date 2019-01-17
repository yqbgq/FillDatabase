package produce;
import types.Type;
import java.util.Random;

public class BitProduce implements IProduce {
    @Override
    public String produce(Type type) {
        if(type.isHasForeignKey()){
            return "(select " + type.getForeignKeyColumn() +
                    " from " + type.getForeignKeyDatabase() + "."+
                    type.getForeignKeyTable() + " order by rand() limit 1)";
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("b'");
        int length = random.nextInt(type.getLength());
        length = length == 0 ? length : length + 1;
        for(int i = 0;i <length ; i++){
            if(random.nextFloat() > 0.5){
                sb.append("1");
            }else {
                sb.append("0");
            }
        }
        sb.append("'");
        return sb.toString();
    }
}
