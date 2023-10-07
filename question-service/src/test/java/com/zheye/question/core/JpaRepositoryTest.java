package com.zheye.question.core;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;


/**
 * 自定义注解，方便进行Spring Jpa 测试
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 测试 JPA 时之开启JPA 相关的环境上下文
 */
@DataJpaTest
/**
 * 引入我们自己配置的数据源
 */
@Import(DatabaseTestConfiguration.class)
/**
 * --------------------------------------------------------------------------
 * Spring 会自动将我们创建的数据源使用 H2 数据源替换，我们需要将这个自动替换的动作关闭
 * --------------------------------------------------------------------------
 */
@AutoConfigureTestDatabase(replace = Replace.NONE)
public @interface JpaRepositoryTest {
}
