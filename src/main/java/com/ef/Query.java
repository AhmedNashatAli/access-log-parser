package com.ef;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ahmed Nashaat on 10/13/2017.
 */
public class Query {
    Query(Param param){
        Predicate<String> validDate= (s)->{
            Date d=new Date();
            String [] splits=s.split("\\|");
            try {
                d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(splits[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(param.getStartDate());
            if(param.getDuration().equals("hourly")){
                c.add(Calendar.HOUR,1);
            }else{
                c.add(Calendar.DATE,1);
            }
            return d.compareTo(param.getStartDate())!=-1&&d.compareTo(c.getTime())!=1;
        };
        try (Stream<String> stream = Files.lines(Paths.get(param.getFilePath()))) {
            Map<String, Long> resultIPAndCount = stream.filter(validDate).map(s->s.split("\\|")[1])
                                              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            resultIPAndCount.entrySet().stream().filter(s->s.getValue()>param.getThreshold())
                            .map(s->s.getKey()).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printResult() {
    }

//    Predicate<Date> validDate= (d)->{
//        Calendar c = Calendar.getInstance();
//        c.setTime(param.getStartDate());
//        if(param.getDuration().equals("hourly")){
//            c.add(Calendar.HOUR,1);
//        }else{
//            c.add(Calendar.DATE,1);
//        }
//        return d.compareTo(param.getStartDate())!=-1&&d.compareTo(c.getTime())!=1;
//    };
}
