package produce;

import java.util.Random;

//TODO 判断非空与否能否创建是空的，而且这个好像判断长度有些不准确
public class FloatProduce implements IProduce {
    @Override
    public String produce(int maxLength) {
        Random random = new Random();
        return String.valueOf(random.nextFloat());
    }
}
