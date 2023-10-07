package com.zheye.question.rest;

import com.zheye.question.domain.application.QuestionApplicationService;
import com.zheye.question.domain.application.command.CreateQuestionCommand;
import com.zheye.question.rest.request.QuestionCommandRequest;
import com.zheye.question.rest.response.QuestionCommandResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionCommandRestController {

    private final QuestionApplicationService questionApplicationService;

    public QuestionCommandRestController(QuestionApplicationService questionApplicationService) {
        this.questionApplicationService = questionApplicationService;
    }

    @PostMapping("/create-question")
    public QuestionCommandResponse createQuestion(@RequestBody QuestionCommandRequest request) {
        var command = new CreateQuestionCommand(request.questionId(), request.title(), request.detail());
        var question = questionApplicationService.createQuestion(command);
        return new QuestionCommandResponse(question.questionId());
    }
}
