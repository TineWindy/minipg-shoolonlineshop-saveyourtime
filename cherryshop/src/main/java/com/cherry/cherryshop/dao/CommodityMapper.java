package com.cherry.cherryshop.dao;

import com.cherry.cherryshop.entity.Commodity;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface CommodityMapper
{
    @Insert("insert into commodity(owner_wxid,name,description,tags,price,origin_price,time,status) values(#{owner_wxid},#{name},#{description},#{tags},#{price},#{origin_price},#{time},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int add(Commodity commodity);

    @Select("select * from commodity where id=#{id}")
    Commodity findCommodityById(@Param("id") int id);

    @Select("select * from commodity where owner_wxid=#{owner_wxid}")
    List<Commodity> findCommodityByOwner(@Param("owner_wxid") int owner_wxid);

    @Delete("delete from commodity where id=#{id}")
    int delete(@Param("id") int id);

    @Select("select * from commodity where tags=#{tags} and status=#{status}")
    List<Commodity> findCommodityByTagsAndStatus(@Param("tags") String tags,@Param("status") int status);

    @Select("select * from commodity where status=#{status}")
    List<Commodity> findCommodityByStatus(@Param("status") int status);

    @Select("select * from commodity where owner_wxid=#{owner_wxid} and status=#{status}")
    List<Commodity> findCommodityByOwnerAndStatus(@Param("owner_wxid") int owner_wxid,@Param("status") int status);

    @Update("update commodity set pictures =#{pictures} where id=#{id}")
    int updatePictures(@Param("pictures") String pictures,@Param("id") int id);

    @Update("update commodity set name=#{name},description=#{description},tags=#{tags},price=#{price},origin_price=#{origin_price},time=#{time},status=#{status} where id=#{id}")
    int updateCommodityInfo(@Param("name") String name,@Param("description") String description,@Param("tags") String tags,@Param("price") double price,@Param("origin_price") double origin_price,
     @Param("time") String time, @Param("status") int status,@Param("id") int id );

    @Select("select * from commodity where status=#{status} and name like CONCAT('%',#{name},'%')")
    List<Commodity> findCommodityByLikeName(@Param("status") int status,@Param("name") String name);
}
