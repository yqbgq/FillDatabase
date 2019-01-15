package Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger  implements ILogger{

    @Override
    public void log(String msg){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        sb.append(df.format(new Date()) );
        sb.append("  ");
        sb.append(Thread.currentThread());
        sb.append("  ");
        sb.append(msg);
        System.out.println(sb.toString());

    }
}
