package Logger;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLogger {
    @Test
    public void print(){
        Thread.currentThread().setName("hahaha");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()) + "  " + Thread.currentThread());// new Date()为获取当前系统时间
    }
}
