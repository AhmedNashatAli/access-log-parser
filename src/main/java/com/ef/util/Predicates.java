package com.ef.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

/**
 * Created by Ahmed Nashaat on 10/15/2017.
 */
public class Predicates {
    public static Predicate<String> validDate(Date startDate, String duration){
        return s-> {
            Date d = new Date();
            String[] splits = s.split("\\|");
            try {
                d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(splits[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            if (duration.equals("hourly")) {
                c.add(Calendar.HOUR, 1);
            } else {
                c.add(Calendar.DATE, 1);
            }
            return d.compareTo(startDate) != -1 && d.compareTo(c.getTime()) != 1;
        };
    };
}
