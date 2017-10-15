package com.ef.core;

import com.ef.util.Predicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ahmed Nashaat on 10/13/2017.
 */
public class Query {
   public static List getIpsMadeRequestsInDuration(Param param){
        List result=new ArrayList();
        try (Stream<String> stream = Files.lines(Paths.get(param.getFilePath()))) {
            Map<String, Long> resultIPAndCount = stream.filter(Predicates.validDate(param.getStartDate(),param.getDuration()))
                                                 .map(s->s.split("\\|")[1])
                                                 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
           result= resultIPAndCount.entrySet().stream().filter(s->s.getValue()>param.getThreshold())
                            .map(s->s.getKey()).collect(Collectors.toList());
         return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }

    }
}
