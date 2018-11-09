package com.wx.sakura.dao;

import com.wx.sakura.entity.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SessionMapperTest {

    @Autowired
    SessionMapper sessionMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    @Ignore
    public void insert() {
        Session session = new Session();
        session.setOpenid("abc");
        session.setSession("abcde");
        session.setWxid(12);
        sessionMapper.insert(session);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectByOpenid() {
        Session session = new Session();
        session = sessionMapper.selectByOpenid("hello");
    }

    @Test
    public void updateSessionByPrimaryKey() {
    }
}