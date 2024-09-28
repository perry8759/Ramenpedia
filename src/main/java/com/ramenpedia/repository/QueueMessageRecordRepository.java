package com.ramenpedia.repository;

import com.ramenpedia.entity.QueueMessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueMessageRecordRepository extends JpaRepository<QueueMessageRecord, Long> {
}
