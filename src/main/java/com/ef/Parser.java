package com.ef;

public class Parser
{

    public static void main(String[] args)
    {
      Param param=new Param(args);
      Query query=new Query(param);
      query.printResult();
    }
}
