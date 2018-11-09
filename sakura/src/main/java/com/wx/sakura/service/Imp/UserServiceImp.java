package com.wx.sakura.service.Imp;

import com.wx.sakura.entity.User;
import com.wx.sakura.dao.UserMapper;
import com.wx.sakura.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

    private final UserMapper  userMapper;

    @Autowired
    public UserServiceImp(UserMapper userMapper){
        this.userMapper  = userMapper;
    }


    @Override
    public boolean deleteByPrimaryKey(Integer wxid){
        int deletecode = userMapper.deleteByPrimaryKey(wxid);
        if(deletecode>0)
            return true;
        else
            return false;
    }

    @Override
    public User getUserbyid(Integer wxid){
        User user = userMapper.selectByPrimaryKey(wxid);
        if(user == null)
            throw new RuntimeException("查无此人");
        else
            return user;
    }

    @Override
    public List<User> queryAllUser(){
        return userMapper.queryAllUser();
    }

    @Override
    public  int register(User user){
       return  userMapper.insert(user);
    }
    @Override
    public boolean updateUserInformation(User user){
        if(user.getName()!=null&&!"".equals(user.getName())){
            if(user.getPhone()!=null&&!"".equals(user.getPhone())){
                if(user.getDormitory()!=null&&!"".equals(user.getDormitory())){
                    if(user.getStudentid()!=null&&!"".equals(user.getStudentid())){
                        try {
                            int insertCount = userMapper.updateByPrimaryKey(user);
                            if (insertCount > 0) {
                                return true;
                            } else {
                                throw new RuntimeException("更新失败");
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("更新失败"+ e.getMessage());
                        }
                    }else {
                        throw new RuntimeException("学号不能为空");
                    }
                }else {
                    throw new RuntimeException("宿舍号不能为空");
                }
            }else {
                throw new RuntimeException("手机号码不能为空");
            }
        }else {
            throw new RuntimeException("姓名不能为空");
        }
    }
}
