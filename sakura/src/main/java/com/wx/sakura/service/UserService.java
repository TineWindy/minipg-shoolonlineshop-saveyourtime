package com.wx.sakura.service;

import com.wx.sakura.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public boolean deleteByPrimaryKey(Integer wxid);

    public User getUserbyid(Integer wxid);

    public  int register(User user);

    public boolean updateUserInformation(User user);

    List<User> queryAllUser();
}
