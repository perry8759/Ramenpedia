CREATE TABLE `city` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `enabled` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `district` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `fk_city_id` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hashtag` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store` (
     `id` BIGINT NOT NULL AUTO_INCREMENT,
     `name` VARCHAR(255) NOT NULL,
     `fk_city_id` VARCHAR(255) NOT NULL,
     `fk_district_id` VARCHAR(255) NOT NULL,
     `address` VARCHAR(255) NOT NULL,
     `description` TEXT,
     `score` DOUBLE NOT NULL,
     `score_count` INT NOT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store_hashtag` (
     `id` BIGINT NOT NULL AUTO_INCREMENT,
     `fk_store_id` BIGINT NOT NULL,
     `fk_hashtag_id` BIGINT NOT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (`fk_store_id`) REFERENCES `store`(`id`),
     FOREIGN KEY (`fk_hashtag_id`) REFERENCES `hashtag`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store_score` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `fk_store_id` BIGINT NOT NULL,
    `fk_member_id` BIGINT NOT NULL,
    `score` DOUBLE NOT NULL,
    `comment` TEXT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_store_id`) REFERENCES `store`(`id`),
    FOREIGN KEY (`fk_member_id`) REFERENCES `member`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;