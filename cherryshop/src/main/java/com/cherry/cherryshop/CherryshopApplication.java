package com.cherry.cherryshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CherryshopApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CherryshopApplication.class, args);
    }
}
