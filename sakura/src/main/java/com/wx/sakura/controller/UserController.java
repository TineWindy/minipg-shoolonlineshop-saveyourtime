package com.wx.sakura.controller;

import com.wx.sakura.entity.Result;
import com.wx.sakura.entity.User;
import com.wx.sakura.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sakura/user")
public class UserController {
    @Autowired
    private UserService userService;
/**
 * 完善\修改个人信息
 */
@RequestMapping(value = "/update",method = RequestMethod.POST)
private Result update (@RequestParam(value = "wxid") int wxid,@RequestParam(value = "name") String name,@RequestParam(value = "studentid") String studentid,@RequestParam(value = "dormitory") String dormitory,@RequestParam(value = "phone") String phone) {
    User user = new User();
    user.setName(name);
    user.setDormitory(dormitory);
    user.setStudentid(studentid);
    user.setPhone(phone);
    user.setWxid(wxid);
    user.setOpenid(userService.getUserbyid(wxid).getOpenid());
    userService.updateUserInformation(user);

    Result result = new Result();
    result.setData(user);
    result.setStatusCode(10000);
    result.setMsg("success");
    return result;
}

/**
 * 根据id查询账号
 */
@RequestMapping(value = "/query",method = RequestMethod.GET)
private Result queryUserById (@RequestParam(value = "wxid") Integer wxid)  {
    User user = userService.getUserbyid(wxid);
    Result result = new Result();
    result.setData(user);
    result.setMsg("success");
    result.setStatusCode(1000);
    return result;
}
/**
 * 查询所有用户
 */
@RequestMapping(value = "/queryall",method = RequestMethod.GET)
private Result queryAllUser(){
    Result result = new Result();
    result.setData(userService.queryAllUser());
    result.setMsg("success");
    result.setStatusCode(1000);
    return result;
}

/**
 * 删除账号
 */
@RequestMapping(value = "/delete",method = RequestMethod.POST)
private Result deleteUserById(@RequestParam(value = "wxid") Integer wxid){
    Boolean status = userService.deleteByPrimaryKey(wxid);
    Result result = new Result();
    result.setData(status);
    result.setMsg("success");
    result.setStatusCode(1000);
    return result;
}


}
