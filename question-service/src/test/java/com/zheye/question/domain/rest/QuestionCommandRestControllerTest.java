package com.zheye.question.domain.rest;

import com.zheye.question.domain.application.QuestionApplicationService;
import com.zheye.question.domain.application.result.QuestionCreatedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class QuestionCommandRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    // SpringTest 这时候不会加载对应的领域服务对应的上下文，自动帮助我们注入一个MockBean,
    // 当我们在测试方法中使用到了这个 mockBean 可以使用 given 等函数来定义对应的行为
    @MockBean
    private QuestionApplicationService questionApplicationService;


    @Test
    void should_return_ok_when_create_question() throws Exception {
        var questionId = "UID_12138";
        given(questionApplicationService.createQuestion(any())).willReturn(new QuestionCreatedResult(questionId));
        var requestBody = new ClassPathResource("request/question/create-question/200-ok.json")
                .getInputStream().readAllBytes();

        mockMvc
                .perform(
                    post("/question/create-question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(questionId));

    }
}
