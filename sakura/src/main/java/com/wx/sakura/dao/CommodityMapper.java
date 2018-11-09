package com.wx.sakura.dao;

import com.wx.sakura.entity.Commodity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityMapper {

    int deleteByPrimaryKey(String commodityId);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(String commodityId);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
}