package util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    private String fromPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private String toPattern = "yyyy-MM-dd HH:mm:ss";

    private String dateString = "2015-01-05T21:19:57Z";

    @Test
    public void test01() {

        try {
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setCalendar(calendar);
            String dateString = "2015-01-05T21:19:57Z";
            System.out.println(sdf.parse(dateString));
            Date date= calendar.getTime();

            System.out.println(date);

            System.out.println(dateString.replace("T"," "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test02() {
        try {
            Date fromDate = DateUtils.parseDate(dateString, fromPattern);
            System.out.println(fromDate.toString());
            String toDate = DateFormatUtils.format(fromDate, toPattern);
            System.out.println(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        System.out.println(MyDateUtil.convert(dateString));
    }
}
