package com.ef;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ahmed Nashaat on 10/13/2017.
 */
public class Param {
    private String filePath;
    private Date startDate;
    private String duration;
    private int threshold;


    public String getFilePath() {
        return filePath;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getDuration() {
        return duration;
    }

    public int getThreshold() {
        return threshold;
    }

    Param(String [] args){
        for (String arg:args) {
            String splits[]=arg.split("=");
            String argName=splits[0].replace("--", "").toLowerCase().trim();
            try {
                switch (argName) {
                    case "accesslog":
                        filePath = splits[1];
                        break;
                    case "duration":
                        duration = splits[1];
                        break;
                    case "startdate":
                        startDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse(splits[1]);
                        break;
                    case "threshold":
                        threshold=Integer.parseInt(splits[1]);break;
                    default: System.out.println("Error parsing no case for "+argName);
                }
            }catch (ParseException e){
                System.out.println("Error parsing : "+argName);
            }
        }
    }
}
