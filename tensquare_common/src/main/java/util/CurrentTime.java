package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/25.10 13
 * @Description:
 */
public class CurrentTime {

    long l = System.currentTimeMillis();
    Date time=new Date(l);
    public String getCurrentTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }


}
