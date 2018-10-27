package com.cherry.cherryshop.entity;


public class Result
{
    //状态码
    private int statusCode;

    //数据
    private Object data;

    //信息
    private String msg;

    public Result(){}

    public Result(int statusCode,Object data,String msg)
    {
        this.statusCode=statusCode;
        this.setData(data);
        this.msg=msg;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }


}
