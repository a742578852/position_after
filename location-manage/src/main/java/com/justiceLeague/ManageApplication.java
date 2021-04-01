package com.justiceLeague;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.justiceLeague.dao")
//开启服务发现
@EnableDiscoveryClient
//开启feign支持
@EnableFeignClients
public class ManageApplication {

    public static void main(String [] args){
        SpringApplication.run(ManageApplication.class,args);
    }
}
