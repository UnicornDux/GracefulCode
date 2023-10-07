create table question_updated_record
(
    question_id     int          not null,
    update_id       int          not null,
    update_type     varchar(255) not null,
    updated_at      timestamp    not null,
    reason          varchar(255),
    created_title   varchar(255),
    created_detail  text,
    unedited_title  varchar(255),
    edited_title    varchar(255),
    unedited_detail text,
    edited_detail   text
) ENGINE=InnoDB charset=utf8mb4;

create index idx_question_updated_record_question_id_updated_at on question_updated_record (question_id, updated_at);