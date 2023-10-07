package com.zheye.question.domain.repository;

import com.zheye.question.domain.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 声明JpsRepository接口, 提供两个泛型参数，第一个是实体类，另一个是主键的类型
 */
public interface QuestionRepository extends JpaRepository<Question, String> {


}
