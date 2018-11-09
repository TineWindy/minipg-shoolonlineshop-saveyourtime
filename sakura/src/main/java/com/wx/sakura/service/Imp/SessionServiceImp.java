package com.wx.sakura.service.Imp;

import com.wx.sakura.dao.SessionMapper;
import com.wx.sakura.entity.Session;
import com.wx.sakura.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImp implements SessionService {

    private SessionMapper sessionMapper;

    @Autowired
    public SessionServiceImp(SessionMapper sessionMapper){
        this.sessionMapper  = sessionMapper;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer wxid) {
        int deletecode =  sessionMapper.deleteByPrimaryKey(wxid);
        if(deletecode>0)
            return true;
        else
            return false;
    }

    @Override
    public int insert(Session session) {
        return sessionMapper.insert(session);
    }

    @Override
    public Session selectByPrimaryKey(Integer wxid) {
        return sessionMapper.selectByPrimaryKey(wxid);
    }

    @Override
    public Session selectByOpenid(String openid) {
        return sessionMapper.selectByOpenid(openid);
    }

    @Override
    public int updateSessionByPrimaryKey(Session record) {
        return sessionMapper.updateSessionByPrimaryKey(record);
    }
}
