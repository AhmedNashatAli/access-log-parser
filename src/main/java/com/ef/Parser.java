package com.ef;

import com.ef.core.Param;
import com.ef.core.Query;

import java.util.List;

public class Parser
{

    public static void main(String[] args)
    {
      Param param=new Param(args);
      Query.getIpsMadeRequestsInDuration(param).stream().forEach(System.out::println);
    }
}
