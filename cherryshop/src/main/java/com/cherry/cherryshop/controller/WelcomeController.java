package com.cherry.cherryshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController
{
    @RequestMapping("/")
    public String index()
    {
        return "You have already connected to the Cherry Shop Web!";
    }
}
