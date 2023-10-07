create table question (
    id          INT(18) PRIMARY KEY AUTO_INCREMENT,
    question_id VARCHAR(50) NOT NULL,
    title       VARCHAR(255) NOT null,
    detail      text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;