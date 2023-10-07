package com.zheye.question.domain.repository;

import com.zheye.question.core.JpaRepositoryTest;
import com.zheye.question.domain.model.entity.Question;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@JpaRepositoryTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_repository_should_successfully_save_question() {
        var question = new Question("UID_0001", "A test title", "A test detail");
        question.editTitle("UID_0002", "for test title", "A new title");
        var savedQuestion = questionRepository.save(question);
        assertThat(savedQuestion.getId(), is(notNullValue()));
        assertSameQuestion(savedQuestion, question);
    }

    @Test
    void test_repository_should_successfully_find_question_by_id() {
        var question = new Question("UID_0001", "A test title", "A test detail");

        // 由于使用了 Spring JPA, 在同一个事务中，save 与 query 的 Entity 是 attach 的
        // 因此，在这里我们在 save 方法之后, 使用 EntityManager 的 detach 方法将其从事务中清除
        var savedQuestion = questionRepository.saveAndFlush(question);
        entityManager.detach(savedQuestion);
        var foundQuestion = questionRepository.findById(savedQuestion.getId()).orElseThrow(AssertionFailedError::new);
        assertSameQuestion(foundQuestion, question);
    }

    private void assertSameQuestion(Question actualQuestion, Question expectedQuestion) {
        assertThat(actualQuestion.getQuestionId(), equalTo(expectedQuestion.getQuestionId()));
        assertThat(actualQuestion.getTitle(), equalTo(expectedQuestion.getTitle()));
        assertThat(actualQuestion.getDetail(), equalTo(expectedQuestion.getDetail()));
        for (int i = 0; i < actualQuestion.getUpdateRecords().size(); i++) {
            // QuestionUpdateRecord 中已经重写的 equals 方法，直接判断对象是否相等即可
            assertThat(actualQuestion.getUpdateRecords().get(i), equalTo(expectedQuestion.getUpdateRecords().get(i)));
        }
    }
}