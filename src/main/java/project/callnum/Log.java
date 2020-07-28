package project.callnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss:SSS");

    public static void info(String msg){
        System.out.println(ft.format( new Date())+" "+msg);
    }
}
