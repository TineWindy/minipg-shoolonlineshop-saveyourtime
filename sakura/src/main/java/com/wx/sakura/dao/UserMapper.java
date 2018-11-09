package com.wx.sakura.dao;

import com.wx.sakura.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer wxid);

    int insert(User record);

    User selectByPrimaryKey(Integer wxid);

    int updateByPrimaryKey(User record);

    List<User> queryAllUser();
}