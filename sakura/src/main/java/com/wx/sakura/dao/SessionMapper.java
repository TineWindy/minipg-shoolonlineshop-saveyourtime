package com.wx.sakura.dao;

import com.wx.sakura.entity.Session;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SessionMapper {

    int deleteByPrimaryKey(Integer wxid);

    int insert(Session session);

    Session selectByPrimaryKey(Integer wxid);

    Session selectByOpenid(String openid);

    int updateSessionByPrimaryKey(Session record);
}