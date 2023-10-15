CREATE TABLE `user` (
    `_id` varchar(64) NOT NULL,
    `email` varchar(64) NOT NULL,
    `nickname` varchar(64) NOT NULL,
    `password` varchar(255) NOT NULL,
    `description` tinytext DEFAULT NULL,
    `avatar` varchar(255) DEFAULT NULL,
    `column` varchar(64) DEFAULT NULL,
    `created_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`_id`,`email`,`nickname`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `columns` (
   `_id` VARCHAR(255) NOT NULL,
   `title` VARCHAR(255) NOT NULL,
   `description` LONGTEXT DEFAULT NULL,
   `author` VARCHAR(255) NOT NULL,
   `avatar` VARCHAR(500) DEFAULT NULL,
   `created_at` TIMESTAMP DEFAULT NOW(),
   PRIMARY KEY(`_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `images` (
      `_id` VARCHAR(255) NOT NULL,
      `url` VARCHAR(500) NOT NULL,
      `created_at` TIMESTAMP DEFAULT NOW(),
      PRIMARY KEY(`_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `posts` (
     `_id` VARCHAR(255) NOT NULL,
     `title` VARCHAR(255) NOT NULL,
     `content` LONGTEXT DEFAULT NULL,
     `excerpt` VARCHAR(255) DEFAULT NULL,
     `column` VARCHAR(255) DEFAULT NULL,
     `created_at` TIMESTAMP DEFAULT NOW(),
     `author_id` VARCHAR(255) NOT NULL,
     `image_id` VARCHAR(255) DEFAULT NULL,
     PRIMARY KEY(`_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;