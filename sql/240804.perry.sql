CREATE TABLE `collect_store` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fk_member_id` BIGINT NOT NULL,
    `fk_store_id` BIGINT NOT NULL,
    CONSTRAINT `fk_collect_store__member` FOREIGN KEY (`fk_member_id`) REFERENCES `member`(`id`),
    CONSTRAINT `fk_collect_store__store` FOREIGN KEY (`fk_store_id`) REFERENCES `store`(`id`),
    UNIQUE KEY `unq_collect_store__member_id__store_id` (`fk_member_id`, `fk_store_id`)
);