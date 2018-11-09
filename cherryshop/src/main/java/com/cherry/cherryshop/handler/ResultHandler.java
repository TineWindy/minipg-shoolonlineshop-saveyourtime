package com.cherry.cherryshop.handler;
import com.cherry.cherryshop.entity.Result;


public class ResultHandler
{
    public static Result resultHandle(int res)
    {
        Result result=new Result();
        if (res>0)
        {
            result.setStatusCode(1000);
            result.setData(res);
            result.setMsg("Success!");
        }
        else
        {
            result.setStatusCode(1001);
            result.setData("");
            result.setMsg("Failed!");
        }
        return result;
    }

    public static <T>Result resultHandle(T element)
    {
        Result result=new Result();
        if (element!=null)
        {
            result.setStatusCode(1000);
            result.setData(element);
            result.setMsg("Success!");
        }
        else
        {
            result.setStatusCode(1001);
            result.setData("");
            result.setMsg("Failed!");
        }
        return result;
    }


}
