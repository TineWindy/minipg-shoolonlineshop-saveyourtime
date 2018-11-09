package com.cherry.cherryshop.entity;


public class Commodity
{
    //商品id
    private int id;

    //拥有者id
    private int owner_wxid;

    //名称
    private String name;

    //图片Url
    private String pictures;

    //商品描述
    private String description;

    //标签
    private String tags;

    //价格
    private double price;

    //原价
    private double origin_price;

    //上传时间
    private String time;

    //状态
    private int status;

    public Commodity(){}

    public Commodity(int owner_wxid,String name,String description,String tags,double price,double origin_price,String time,int status)
    {
        this.owner_wxid=owner_wxid;
        this.name=name;
        this.description=description;
        this.tags=tags;
        this.price=price;
        this.origin_price=origin_price;
        this.time=time;
        this.status=status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getOwner_wxid()
    {
        return owner_wxid;
    }

    public void setOwner_wxid(int owner_wxid)
    {
        this.owner_wxid = owner_wxid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPictures()
    {
        return pictures;
    }

    public void setPictures(String pictures)
    {
        this.pictures = pictures;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getOrigin_price()
    {
        return origin_price;
    }

    public void setOrigin_price(double origin_price)
    {
        this.origin_price = origin_price;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
