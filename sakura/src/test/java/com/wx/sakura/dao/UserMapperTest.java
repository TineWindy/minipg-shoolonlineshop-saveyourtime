package com.wx.sakura.dao;

import com.wx.sakura.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        User user = new User();
        user.setOpenid("abcdefg");
        System.out.println(user.getOpenid());
        userMapper.insert(user);
    }

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(14);
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void queryAllUser() {
    }
}