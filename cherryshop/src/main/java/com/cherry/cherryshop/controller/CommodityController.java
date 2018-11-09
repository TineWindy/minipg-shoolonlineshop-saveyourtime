package com.cherry.cherryshop.controller;

import com.cherry.cherryshop.entity.Commodity;
import com.cherry.cherryshop.entity.Result;
import com.cherry.cherryshop.handler.ResultHandler;
import com.cherry.cherryshop.service.CommodityService;
import com.cherry.cherryshop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController
{
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/post",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
    public Result postCommodity(@RequestParam(value = "owner_wxid") int owner_wxid , @RequestParam(value = "name") String name, @RequestParam(value = "description") String description, @RequestParam(value = "tags") String tags,
      @RequestParam(value = "price") double price, @RequestParam(value = "origin_price") double origin_price, @RequestParam(value = "time") String time, @Valid @RequestParam(value = "status") int status,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            Commodity commodity=new Commodity(owner_wxid,name,description,tags,price,origin_price,time,status);
            commodityService.add(commodity);
            return ResultHandler.resultHandle(commodity.getId());
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }

    }

    @RequestMapping(value = "/getById" ,method = RequestMethod.GET)
    public Result getCommodityById(@RequestParam(value = "id") int id,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            Commodity commodity=commodityService.findCommodityById(id);
            return ResultHandler.resultHandle(commodity);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }

    }

    @RequestMapping(value = "/getByOwner",method = RequestMethod.GET)
    public Result getCommodityByOwner(@RequestParam(value = "owner_wxid") int owner_wxid,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            List<Commodity> commodityList=commodityService.findCommodityByOwner(owner_wxid);
            return ResultHandler.resultHandle(commodityList);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");

        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
    public Result deleteCommodity(@RequestParam(value = "id") int id,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            int res=commodityService.delete(id);
            return ResultHandler.resultHandle(res);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }

    }

    @RequestMapping(value = "/getByTagsAndStatus",method = RequestMethod.GET)
    public Result getCommodityByTagsAndStatus(@RequestParam(value = "tags") String tags,@RequestParam(value = "status") int status,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            List<Commodity> commodityList=commodityService.findCommodityByTagsAndStatus(tags,status);
            return ResultHandler.resultHandle(commodityList);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }

    }

    @RequestMapping(value = "/getByStatus" ,method = RequestMethod.GET)
    public Result getCommodityByStatus(@RequestParam(value = "status") int status,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            List<Commodity> commodityList=commodityService.findCommodityByStatus(status);
            return ResultHandler.resultHandle(commodityList);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }

    }

    @RequestMapping(value = "/getByOwnerAndStatus",method = RequestMethod.GET)
    public Result getCommodityByOwnerAndStatus(@RequestParam(value = "owner_wxid") int owner_wxid,@RequestParam(value = "status") int status,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            List<Commodity> commodityList=commodityService.findCommodityByOwnerAndStatus(owner_wxid,status);
            return ResultHandler.resultHandle(commodityList);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }

    }

    @RequestMapping(value = "/updateCommodityInfo",method = RequestMethod.POST,consumes ="application/x-www-form-urlencoded" )
    public Result updateCommodityInfo(@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("tags") String tags,@RequestParam("price") double price,@RequestParam("origin_price") double origin_price,
       @RequestParam("time") String time, @RequestParam("status") int status,@RequestParam("id") int id,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            int res=commodityService.updateCommodityInfo(name, description, tags, price, origin_price, time, status, id);
            return ResultHandler.resultHandle(res);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }
    }

    @RequestMapping(value = "/updatePictures")
    public Result updatePictures(@RequestParam("id") int id,@RequestParam("picUrl") String pictureUrl)
    {
        System.out.println(id+" "+pictureUrl);
        Commodity commodity=commodityService.findCommodityById(id);
        String newPictures;
        System.out.println(commodity.getPictures());
        if (commodity.getPictures()!=null&&!commodity.getPictures().equals(""))
        {
            newPictures=commodity.getPictures()+"-"+pictureUrl;
        }
        else
        {
            newPictures=pictureUrl;
        }
        commodityService.updatePictures(newPictures, id);
        return  ResultHandler.resultHandle(pictureUrl);
    }

    @RequestMapping(value = "/getCommodityByLikeName" ,method = RequestMethod.GET)
    public Result getCommodityByLikeName(@RequestParam("status") int status,@RequestParam("name") String name,@RequestHeader(value = "session") String session)
    {
        if (checkSession(session))
        {
            List<Commodity> commodityList=commodityService.findCommodityByLikeName(status,name);
            return ResultHandler.resultHandle(commodityList);
        }
        else
        {
            return new Result(1005,"","The server has rejected your request");
        }
    }

    public boolean checkSession(String session)
    {
      if(sessionService.checkSession(session)!=null)
      {
        return true;
      }
      return false;
    }
}
