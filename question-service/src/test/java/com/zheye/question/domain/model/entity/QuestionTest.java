package com.zheye.question.domain.model.entity;

import com.zheye.question.domain.model.vo.QuestionUpdateRecord;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class QuestionTest {

    @Test
    void should_generate_created_record_after_question_created(){
        var question = new Question("UID_0001", "question", "answer");
        var updateRecords = question.getUpdateRecords();
        assertThat(updateRecords, hasSize(1));
        var questionUpdateRecord = updateRecords.get(0);
        assertThat(questionUpdateRecord.getUpdateType(), equalTo(QuestionUpdateRecord.UpdateType.CREATED));
        assertThat(questionUpdateRecord.getCreatedTitle(), equalTo(question.getTitle()));
        assertThat(questionUpdateRecord.getCreatedDetail(), equalTo(question.getDetail()));
    }


    @Test
    void should_generate_edited_record_after_question_edited(){
        String originalTitle = "a test title";
        String originalDetail = "a test detail";
        String editedTitle = "a new title";
        String editedDetail = "a new detail";
        var question = new Question("UID_0001",originalTitle , originalDetail);
        question.editTitle("UID_0002", "for test title", editedTitle);
        question.editDetail("UID_0003", "for test detail", editedDetail);
        var updateRecords = question.getUpdateRecords();
        assertThat(updateRecords, hasSize(3));
        // 创建的记录
        var questionUpdateRecord = updateRecords.get(0);
        assertThat(questionUpdateRecord.getUpdateType(), equalTo(QuestionUpdateRecord.UpdateType.CREATED));
        assertThat(questionUpdateRecord.getCreatedTitle(), equalTo(originalTitle));
        assertThat(questionUpdateRecord.getCreatedDetail(), equalTo(originalDetail));
        // 标题修改记录
        var questionTitleEditedRecord = updateRecords.get(1);
        assertThat(questionTitleEditedRecord.getUpdateType(), equalTo(QuestionUpdateRecord.UpdateType.TITLE_EDITED));
        assertThat(questionTitleEditedRecord.getUnEditedTitle(), equalTo(originalTitle));
        assertThat(questionTitleEditedRecord.getEditedTitle(), equalTo(editedTitle));
        // 内容修改记录
        var questionDetailEditedRecord = updateRecords.get(2);
        assertThat(questionDetailEditedRecord.getUpdateType(), equalTo(QuestionUpdateRecord.UpdateType.DETAIL_EDITED));
        assertThat(questionDetailEditedRecord.getUnEditedDetail(), equalTo(originalDetail));
        assertThat(questionDetailEditedRecord.getEditedDetail(), equalTo(editedDetail));

        // Question 对象的属性是否被修改
        assertThat(question.getTitle(), equalTo(editedTitle));
        assertThat(question.getDetail(), equalTo(editedDetail));
    }
}