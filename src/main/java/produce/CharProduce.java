package produce;

import java.util.Random;

public class CharProduce {
    public  static String produce(int maxLength){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int count = random.nextInt(maxLength);
        for(int i=0;i<count;i++){
            sb.append((char)random.nextInt());
        }
        return sb.toString();
    }


}
