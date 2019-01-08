package ua.khpi.baturin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {

    public static String parse(String some) throws ParseException {
        Locale bLocale = new Locale.Builder().setLanguage("ru").setRegion("RU").build();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", bLocale);
        String dateString = format.format(new Date());
        Date date = format.parse(some);
        SimpleDateFormat simpleDateformat;
        simpleDateformat = new SimpleDateFormat("EEEE", bLocale);
        return simpleDateformat.format(date);

    }

}
