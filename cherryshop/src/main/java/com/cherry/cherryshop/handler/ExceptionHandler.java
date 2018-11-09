package com.cherry.cherryshop.handler;

import java.util.Map;

public class ExceptionHandler
{

    public static Map<String, Object> exceptionHandler(Map<String, Object> map)
    {
        String message=(String)map.get("message");
        map.clear();
        if (message.startsWith("Required")&&message.endsWith("is not present"))
        {
            map.put("statusCode","1002");
            map.put("msg",message);
            map.put("data","");
        }
        else if (message.startsWith("Failed to convert value"))
        {
            map.put("statusCode","1003");
            map.put("msg",message);
            map.put("data","");
        }
        else
        {
            map.put("statusCode","1004");
            map.put("msg",message);
            map.put("data","");
        }
        return map;
    }


}
