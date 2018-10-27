package com.cherry.cherryshop.service;

import com.cherry.cherryshop.dao.CommodityMapper;
import com.cherry.cherryshop.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService implements CommodityMapper
{
    @Autowired(required = true)
    private CommodityMapper commodityMapper;

    public int add(Commodity commodity)
    {
        return commodityMapper.add(commodity);
    }

    public Commodity findCommodityById(int id)
    {
        return commodityMapper.findCommodityById(id);
    }

    public List<Commodity> findCommodityByOwner(int owner_wxid)
    {
        return commodityMapper.findCommodityByOwner(owner_wxid);
    }

    public int delete(int id)
    {
        return commodityMapper.delete(id);
    }

    public List<Commodity> findCommodityByTagsAndStatus(String tags,int status)
    {
        return commodityMapper.findCommodityByTagsAndStatus(tags,status);
    }

    public List<Commodity> findCommodityByStatus(int status)
    {
        return commodityMapper.findCommodityByStatus(status);
    }

    public List<Commodity> findCommodityByOwnerAndStatus(int owner_wxid,int status)
    {
        return commodityMapper.findCommodityByOwnerAndStatus(owner_wxid,status);
    }

    public int updatePictures(String pictures,int id)
    {
        return commodityMapper.updatePictures(pictures,id);
    }

    public int updateCommodityInfo(String name,String description, String tags,double price,double origin_price,String time, int status,int id)
    {
        return commodityMapper.updateCommodityInfo(name, description, tags, price, origin_price, time, status, id);
    }

    public List<Commodity> findCommodityByLikeName(int status,String name)
    {
        return commodityMapper.findCommodityByLikeName(status,name);
    }

}
