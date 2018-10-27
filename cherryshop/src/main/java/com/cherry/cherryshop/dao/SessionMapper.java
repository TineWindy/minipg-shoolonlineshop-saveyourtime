package com.cherry.cherryshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SessionMapper
{
    @Select("select * from session_info where session=#{session} ")
    Object checkSession(@Param(value = "session") String session );
}
