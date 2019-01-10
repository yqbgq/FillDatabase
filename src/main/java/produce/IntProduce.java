package produce;

import java.util.Random;

public class IntProduce {
    public static String produce(int maxLength){
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
