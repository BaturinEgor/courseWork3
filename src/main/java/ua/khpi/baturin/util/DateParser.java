package ua.khpi.baturin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static String parse(String some) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        Date date = format.parse(some);
        SimpleDateFormat simpleDateformat;
        simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(date);

    }

}
