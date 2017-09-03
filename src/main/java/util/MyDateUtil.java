package util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;

public class MyDateUtil {


    private static String fromPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static String toPattern = "yyyy-MM-dd HH:mm:ss";


    public static String convert(String datetime) {
        String toDate = null;
        try {
            toDate= DateFormatUtils.format(DateUtils.parseDate(datetime, fromPattern), toPattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toDate;
    }

}
