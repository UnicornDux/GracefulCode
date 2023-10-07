package com.zheye.question.domain.application;

import com.zheye.question.domain.application.command.CreateQuestionCommand;
import com.zheye.question.domain.application.result.QuestionCreatedResult;
import com.zheye.question.domain.model.entity.Question;
import com.zheye.question.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionApplicationService {

    /**
     * 将成员定义成 final 的时候必须要在构造方法中进行赋值，可以避免我们忘记写构造方法
     */
    private final QuestionRepository questionRepository;

    /**
     * 使用构造方法的方式来进行 Bean 注入的时候，可以减少 Spring 反射注入成员的方式耦合
     * -------------
     * @param questionRepository
     */
    public QuestionApplicationService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionCreatedResult createQuestion(CreateQuestionCommand command) {
        var question = new Question(command.questionId(), command.title(), command.detail());
        questionRepository.save(question);
        return new QuestionCreatedResult(question.getId());
    }
}
