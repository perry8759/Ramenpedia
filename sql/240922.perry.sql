ALTER TABLE store ADD COLUMN status VARCHAR(255);

CREATE TABLE `store_business_hours` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `fk_store_id` BIGINT NOT NULL,
    `day_of_week` INT NOT NULL,
    `open_time` VARCHAR(255) NOT NULL,
    `close_time` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`fk_store_id`) REFERENCES `store`(`id`)
);