package com.justiceLeague.intecpter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置器
 */

@Configuration
public class CustomWebContigurer implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry){


        //registry.addInterceptor(loginIntercepter).addPathPatterns("/api/us/login/getHomePage");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
