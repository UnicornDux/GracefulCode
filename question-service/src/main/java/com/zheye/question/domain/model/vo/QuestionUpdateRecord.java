package com.zheye.question.domain.model.vo;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.PersistenceConstructor;


import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class QuestionUpdateRecord {

    /**
     * 枚举类的序列化持久化的策略
     */
    @Enumerated(EnumType.STRING)
    private UpdateType updateType;
    private String updateId;
    private OffsetDateTime updatedAt;
    private String createdTitle;
    private String createdDetail;
    private String reason;
    private String unEditedTitle;
    private String editedTitle;
    private String unEditedDetail;
    private String editedDetail;

    public enum UpdateType {
        CREATED, TITLE_EDITED, DETAIL_EDITED
    }
    public QuestionUpdateRecord(
            UpdateType updateType,
            String updateId,
            OffsetDateTime updatedAt,
            String createdTitle,
            String createdDetail,
            String reason,
            String unEditedTitle,
            String editedTitle,
            String unEditedDetail,
            String editedDetail
    ) {
        this.updateType = updateType;
        this.updateId = updateId;
        this.updatedAt = updatedAt;
        this.createdTitle = createdTitle;
        this.createdDetail = createdDetail;
        this.reason = reason;
        this.unEditedTitle = unEditedTitle;
        this.editedTitle = editedTitle;
        this.unEditedDetail = unEditedDetail;
        this.editedDetail = editedDetail;
    }

    @PersistenceConstructor
    protected QuestionUpdateRecord() {}

    /**
     * 创建问题
     * @param updateId
     * @param createdTitle
     * @param createdDetail
     * @return
     */
    public static QuestionUpdateRecord ofCreated(
            String updateId,  String createdTitle, String createdDetail
    ) {
        return new QuestionUpdateRecord(
                UpdateType.CREATED,
                updateId,
                OffsetDateTime.now(),
                createdTitle,
                createdDetail,
                null,
                null, null, null, null
        );
    }

    /**
     * 编辑标题
     * @param updateId
     * @param reason
     * @param unEditedTitle
     * @param editedTitle
     * @return
     */
    public static QuestionUpdateRecord ofTitleEdited(
            String updateId, String reason, String unEditedTitle, String editedTitle
    ) {
        return new QuestionUpdateRecord(
                UpdateType.TITLE_EDITED,
                updateId,
                OffsetDateTime.now(),
                null,
                null,
                reason,
                unEditedTitle,
                editedTitle,
                null,
                null
        );
    }

    /**
     * 编辑详情
     * @param updateId
     * @param reason
     * @param unEditedDetail
     * @param editedDetail
     * @return
     */
    public static QuestionUpdateRecord ofDetailEdited(
            String updateId, String reason, String unEditedDetail, String editedDetail
    ) {
        return new QuestionUpdateRecord(
                UpdateType.DETAIL_EDITED,
                updateId,
                OffsetDateTime.now(),
                null,
                null,
                reason,
                null,
                null,
                unEditedDetail,
                editedDetail
        );
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public String getUpdateId() {
        return updateId;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedTitle() {
        return createdTitle;
    }

    public String getCreatedDetail() {
        return createdDetail;
    }

    public String getReason() {
        return reason;
    }

    public String getUnEditedTitle() {
        return unEditedTitle;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public String getUnEditedDetail() {
        return unEditedDetail;
    }

    public String getEditedDetail() {
        return editedDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  QuestionUpdateRecord that)) return false;
        return updateType == that.updateType
                && Objects.equals(updateId, that.updateId)
                && Objects.equals(updatedAt, that.updatedAt)
                && Objects.equals(createdTitle, that.createdTitle)
                && Objects.equals(createdDetail, that.createdDetail)
                && Objects.equals(reason, that.reason)
                && Objects.equals(unEditedTitle, that.unEditedTitle)
                && Objects.equals(editedTitle, that.editedTitle)
                && Objects.equals(unEditedDetail, that.unEditedDetail)
                && Objects.equals(editedDetail, that.editedDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getUpdateType(),
                getUpdateId(),
                getUpdatedAt(),
                getCreatedTitle(),
                getCreatedDetail(),
                getReason(),
                getUnEditedTitle(),
                getEditedTitle(),
                getUnEditedDetail(),
                getEditedDetail()
        );
    }
}
