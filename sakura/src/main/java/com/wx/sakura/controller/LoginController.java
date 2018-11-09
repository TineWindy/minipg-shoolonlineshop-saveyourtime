package com.wx.sakura.controller;


import com.alibaba.fastjson.JSONObject;
import com.wx.sakura.entity.Result;
import com.wx.sakura.entity.Session;
import com.wx.sakura.entity.User;
import com.wx.sakura.service.SessionService;
import com.wx.sakura.service.UserService;
import com.wx.sakura.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/sakura")
public class LoginController {

    @Autowired
    SessionService sessionService;
    @Autowired
    UserService userService;

    int wxid;

    @RequestMapping(value = "/",method = GET)
        public String index()
        {
            return "You have already connected to the Cherry Shop Web!";
        }

    @RequestMapping(value = "/WXLogin", method = RequestMethod.POST)
    @CrossOrigin(methods = POST)
   public Result login(@RequestParam(value = "code")  String code) {

       Result result = new Result();
        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            result.setStatusCode(10001);
            result.setMsg("code 不能为空");
            return result;
        }
        
        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wx7b749e5501d908b3";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "4f85f0b53731de7746977ae35e0c10d2";
        // 授权类型
        String grant_type = "authorization_code";

        //向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        System.out.println(params);

        // 解析相应内容（转换成json对象)
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //System.out.println(sr);
        JSONObject json = JSONObject.parseObject(sr);

        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        System.out.println("session_key: "+session_key);
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        System.out.println("openid："+openid);

        String sessionStr = openid+session_key;
        String md5 =  DigestUtils.md5DigestAsHex(sessionStr.getBytes());

        System.out.println(md5);

        /**
         *第一次登陆，新建用户，将生成的openid存入session_info
         * 否则更新session
         * TODO: 检查session_key的有效性
         * */
        if(sessionService.selectByOpenid(openid) == null) {
            User user = new User();
            user.setOpenid(openid);
            userService.register(user);
            wxid = user.getWxid();

            Session session = new Session();
            session.setWxid(wxid);
            session.setOpenid(openid);
            session.setSession(md5);
            sessionService.insert(session);
            result.setData(session);
        }
        else{
            Session session = sessionService.selectByOpenid(openid);
            System.out.println("    "+session.getWxid()+session.getSession() );
            session.setSession(md5);
            System.out.println("     "+session.getWxid()+session.getSession() );
            sessionService.updateSessionByPrimaryKey(session);
            result.setData(session);
        }

        result.setMsg("success");
        result.setStatusCode(10000);

        return result;
    }
}