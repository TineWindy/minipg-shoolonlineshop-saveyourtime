package com.cherry.cherryshop.handler;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;


import java.util.Map;


@Component
public class MyErrorAttributes extends DefaultErrorAttributes
{
    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace)
    {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        return ExceptionHandler.exceptionHandler(map);
    }

}

