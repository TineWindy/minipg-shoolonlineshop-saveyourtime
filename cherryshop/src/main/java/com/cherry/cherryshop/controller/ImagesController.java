package com.cherry.cherryshop.controller;

import com.cherry.cherryshop.configuration.ConstantQiniu;
import com.cherry.cherryshop.entity.Result;
import com.cherry.cherryshop.service.SessionService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class ImagesController
{
    @Autowired
    private ConstantQiniu constantQiniu;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/post/qiniu")
    @ResponseBody
    public Result uploadImgQiniu(@RequestParam("imageFile") MultipartFile multipartFile, @RequestParam("id") int id, HttpServletResponse response, @RequestHeader(value = "session") String session) throws IOException
    {

       if (checkSession(session))
       {
           FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
           String key=Integer.toString(id)+"cdy"+UUID.randomUUID().toString().replace("-", "");
           String path = uploadQNImg(inputStream,key);
           if (!path.equals(""))
           {
               String redirectUrl="http://10.133.176.155:8080/commodity/updatePictures?id="+Integer.toString(id)+"&picUrl="+key;
               response.sendRedirect(redirectUrl);
           }
           else
           {
               return new Result(1004,"","Failed to upload the pictures");
           }
       }
       else
       {
           return new Result(1005,"","The server has rejected your request");
       }
       return null;
    }

    /**
     * 将图片上传到七牛云
     */
    private String uploadQNImg(FileInputStream file, String key)
    {
        // 构造一个带指定Zone对象的配置类

        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try
        {
            Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
            String upToken = auth.uploadToken(constantQiniu.getBucket());
            try
            {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String returnPath = constantQiniu.getPath() + "/" + putRet.key;
                return returnPath;
            }
            catch (QiniuException ex)
            {
                Response r = ex.response;
                System.err.println(r.toString());
                try
                {
                    System.err.println(r.bodyString());
                }
                catch (QiniuException ex2)
                {
                    //ignore
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/delete/qiniu")
    @ResponseBody
    public String deleteImgQiniu(@RequestParam("imageFile") String key, HttpServletResponse response,@RequestHeader(value = "session") String session) throws IOException
    {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try
        {
            bucketManager.delete(constantQiniu.getBucket(), key);
//            String redirectUrl="http://10.133.176.155:8080/commodity/updatePictures?id="+Integer.toString(id)+"&picUrl="+key;
//            response.sendRedirect(redirectUrl);
        }
        catch (QiniuException ex)
        {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

        return "";
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
