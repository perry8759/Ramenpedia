ALTER TABLE `member`
    ADD COLUMN `privacy_policy_millis` BIGINT(20);

ALTER TABLE `member`
    ADD COLUMN `img` BLOB;

ALTER TABLE `member`
    ADD COLUMN `create_millis` BIGINT(20);