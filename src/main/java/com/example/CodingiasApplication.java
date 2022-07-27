package com.example;

import com.example.service.JpaGrayCodeTaskLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CodingiasApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CodingiasApplication.class, args);
        ctx.getBean(JpaGrayCodeTaskLoader.class).loadAll();
    }
}
