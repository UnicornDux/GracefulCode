package com.zheye.question.rest.request;

public record QuestionCommandRequest(
        String questionId,
        String title,
        String detail
) {
}
