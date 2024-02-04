package com.peaq.ec3.shopp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.peaq.ec3.shopp.mapper")
@SpringBootApplication
public class Ec3ShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ec3ShoppingApplication.class, args);
    }

}
