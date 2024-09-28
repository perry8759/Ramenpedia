ALTER TABLE store ADD COLUMN status VARCHAR(255);

CREATE TABLE `store_business_hours` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `fk_store_id` BIGINT NOT NULL,
    `day_of_week` INT NOT NULL,
    `open_time` VARCHAR(255) NOT NULL,
    `close_time` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`fk_store_id`) REFERENCES `store`(`id`)
);

CREATE TABLE `queue_message_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `type` ENUM('BUSINESS_HOURS', 'LIMITED', 'QUEUE') NOT NULL,
    `message` TEXT,
    `fk_member_id` BIGINT,
    `fk_store_id` BIGINT,
    `create_millis` BIGINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_member_id`) REFERENCES `member`(`id`),
    FOREIGN KEY (`fk_store_id`) REFERENCES `store`(`id`)
);