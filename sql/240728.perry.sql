CREATE TABLE `member_hashtag` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fk_member_id` BIGINT NOT NULL,
    `fk_hashtag_id` BIGINT NOT NULL,
    CONSTRAINT `fk_member_hashtag__member` FOREIGN KEY (`fk_member_id`) REFERENCES `member`(`id`),
    CONSTRAINT `fk_member_hashtag__hashtag` FOREIGN KEY (`fk_hashtag_id`) REFERENCES `hashtag`(`id`),
    UNIQUE KEY `unq_member_hashtag__member_id__hashtag_id` (`fk_member_id`, `fk_hashtag_id`)
);