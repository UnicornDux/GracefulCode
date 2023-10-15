package com.zheye.question.domain.model.entity;

import com.zheye.question.domain.model.vo.QuestionUpdateRecord;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private String questionId;
    private String title;
    private String detail;

    /**
     * 关联表
     */
    @ElementCollection
    @CollectionTable(name="question_updated_record")
    // 指定查询时候的排序字段
    @OrderBy("updatedAt")
    private List<QuestionUpdateRecord> updateRecords;


    public Question(String questionId, String title, String detail) {
        this.questionId = questionId;
        this.title = title;
        this.detail = detail;
        this.updateRecords = new ArrayList<>();
        updateRecords.add(QuestionUpdateRecord.ofCreated(questionId, title, detail));
    }

    protected Question() {}

    public String getId() {
        return id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }



    public List<QuestionUpdateRecord> getUpdateRecords() {
        return Collections.unmodifiableList(updateRecords);
    }

    public void editTitle(String editorId, String reason, String title) {
        this.updateRecords.add(QuestionUpdateRecord.ofTitleEdited(editorId, reason, this.title, title));
        this.title = title;
    }

    public void editDetail(String editorId, String reason, String detail) {
        this.updateRecords.add(QuestionUpdateRecord.ofDetailEdited(editorId, reason, this.detail, detail));
        this.detail = detail;
    }
}
