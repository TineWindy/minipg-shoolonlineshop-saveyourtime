package com.wx.sakura.entity;

public class Session {
    int wxid;
    String session;
    String openid;

    public int getWxid() {
        return wxid;
    }

    public void setWxid(int wxid) {
        this.wxid = wxid;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
