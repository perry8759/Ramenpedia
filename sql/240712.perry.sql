ALTER TABLE `member`
    ADD COLUMN `privacy_policy_millis` BIGINT(20);

ALTER TABLE `member`
    ADD COLUMN `img` BLOB;

ALTER TABLE `member`
    ADD COLUMN `create_millis` BIGINT(20);

ALTER TABLE `member`
    ADD COLUMN `uid` VARCHAR(10),
    ADD CONSTRAINT `unq_member_uid` UNIQUE (`uid`);

ALTER TABLE `member`
    ADD COLUMN `nickname` VARCHAR(10);

ALTER TABLE `member`
    MODIFY `birthday` varchar(20) NULL;