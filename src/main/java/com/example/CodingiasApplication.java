package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CodingiasApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CodingiasApplication.class, args);
        ctx.getBean(GrayCodeTaskLoader.class).loadAll();
    }
}
