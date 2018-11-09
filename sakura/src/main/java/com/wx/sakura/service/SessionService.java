package com.wx.sakura.service;

import com.wx.sakura.entity.Session;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {

    boolean deleteByPrimaryKey(Integer wxid);

    int insert(Session session);

    Session selectByPrimaryKey(Integer wxid);

    Session selectByOpenid(String openid);

    int updateSessionByPrimaryKey(Session record);
}
