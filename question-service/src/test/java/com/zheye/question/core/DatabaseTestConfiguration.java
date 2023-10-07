package com.zheye.question.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.sql.DataSource;


public class DatabaseTestConfiguration {
    /**
     * > 1.指定数据库容器初始化时执行 start 方法创建， 并在测试完成后执行 stop 方法删除
     * > 2.指定创建容器的数据库版本
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public MySQLContainer<?> mySQLContainer() {
        // 创建容器，等待容器初始化好(Wait.forListeningPort)，才能获取到容器的链接信息()
        return new MySQLContainer<>("mysql:8.0")
                .waitingFor(Wait.forListeningPort());
    };

    /**
     * > 指定数据源, 配置的数据源Bean对象从创建的容器中获取到对应的数据库链接信息
     * > 指定我们执行数据库的 `flyway` 的数据源也要使用到这个数据源
     * @return
     */
    @Bean
    @FlywayDataSource
    public DataSource dataSource(MySQLContainer<?> mysql) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mysql.getJdbcUrl());
        hikariConfig.setUsername(mysql.getUsername());
        hikariConfig.setPassword(mysql.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
