package com.cherry.cherryshop.service;

import com.cherry.cherryshop.dao.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements SessionMapper
{
    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public Object checkSession(String session)
    {
        return sessionMapper.checkSession(session);
    }
}
