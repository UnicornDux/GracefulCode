package com.zheye.column;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(value = "*")
@MapperScan(basePackages = "com.zheye.column.domain.mapper")
@SpringBootApplication
public class ColumnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ColumnApplication.class, args);
    }
}