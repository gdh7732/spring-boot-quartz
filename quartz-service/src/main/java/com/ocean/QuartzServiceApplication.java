package com.ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * 启动类
 *
 * @author guodahai
 */
@SpringBootApplication
@DubboComponentScan("com.ocean.service")
public class QuartzServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzServiceApplication.class, args);
    }

}
