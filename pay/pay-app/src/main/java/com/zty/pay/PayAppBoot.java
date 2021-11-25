package com.zty.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: tianyi.zeng
 * @create: 25/11/2021 - 下午 10:06
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zty.pay.dao"})
@ComponentScan(basePackages = {"com.zty"})
public class PayAppBoot {

    public static void main(String[] args) {
        SpringApplication.run(PayAppBoot.class, args);
    }

}
