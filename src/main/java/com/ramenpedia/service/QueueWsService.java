package com.ramenpedia.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramenpedia.entity.Member;
import com.ramenpedia.entity.QueueMessageRecord;
import com.ramenpedia.entity.Store;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.enumerate.StoreBusinessStatus;
import com.ramenpedia.exception.BusinessException;
import com.ramenpedia.handler.dto.*;
import com.ramenpedia.repository.MemberRepository;
import com.ramenpedia.repository.QueueMessageRecordRepository;
import com.ramenpedia.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QueueWsService {

    @Autowired
    private QueueMessageRecordRepository queueMessageRecordRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberRepository memberRepository;

    public BusinessHoursSend businessHours(StoreBusinessStatus status, Long storeId, String email) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        QueueMessageRecord queueMessageRecord = queueMessageRecordRepository.save(
                new QueueMessageRecord(member.getId(), store.getId(),
                QueueMessageRecord.Type.BUSINESS_HOURS, status.toString()));
        return new BusinessHoursSend(member.getId(), QueueMessageRecord.Type.BUSINESS_HOURS, status,
                queueMessageRecord.getCreateMillis());
    }

    public QueueSend queue(Integer nowQueuePersonCount, Long storeId, String email) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        QueueMessageRecord queueMessageRecord = queueMessageRecordRepository.save(
                new QueueMessageRecord(member.getId(), store.getId(),
                        QueueMessageRecord.Type.QUEUE, nowQueuePersonCount.toString()));
        return new QueueSend(member.getId(), QueueMessageRecord.Type.QUEUE, nowQueuePersonCount,
                queueMessageRecord.getCreateMillis());
    }

    public LimitedSend limited(Integer remainingQuantity, Long storeId, String email) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        QueueMessageRecord queueMessageRecord = queueMessageRecordRepository.save(
                new QueueMessageRecord(member.getId(), store.getId(),
                        QueueMessageRecord.Type.LIMITED, remainingQuantity.toString()));
        return new LimitedSend(member.getId(), QueueMessageRecord.Type.LIMITED, remainingQuantity,
                queueMessageRecord.getCreateMillis());
    }
}
