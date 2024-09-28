package com.ramenpedia.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramenpedia.controller.websocket.dto.*;
import com.ramenpedia.entity.Member;
import com.ramenpedia.entity.QueueMessageRecord;
import com.ramenpedia.entity.Store;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.enumerate.StoreBusinessStatus;
import com.ramenpedia.exception.BusinessException;
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

    public BusinessHoursSend businessHours(Long storeId, BusinessHoursMessage message, String email) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        QueueMessageRecord queueMessageRecord = queueMessageRecordRepository.save(
                new QueueMessageRecord(member.getId(), store.getId(),
                QueueMessageRecord.Type.BUSINESS_HOURS, objectMapper.writeValueAsString(message)));
        return new BusinessHoursSend(member.getId(), QueueMessageRecord.Type.BUSINESS_HOURS, message.getStatus(),
                queueMessageRecord.getCreateMillis());
    }

    public QueueSend queue(Long storeId, QueueMessage message, String email) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        QueueMessageRecord queueMessageRecord = queueMessageRecordRepository.save(
                new QueueMessageRecord(member.getId(), store.getId(),
                        QueueMessageRecord.Type.QUEUE, objectMapper.writeValueAsString(message)));
        return new QueueSend(member.getId(), QueueMessageRecord.Type.QUEUE, message.getNowQueuePersonCount(),
                queueMessageRecord.getCreateMillis());
    }

    public LimitedSend limited(Long storeId, LimitedMessage message, String email) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        QueueMessageRecord queueMessageRecord = queueMessageRecordRepository.save(
                new QueueMessageRecord(member.getId(), store.getId(),
                        QueueMessageRecord.Type.LIMITED, objectMapper.writeValueAsString(message)));
        return new LimitedSend(member.getId(), QueueMessageRecord.Type.LIMITED, message.getRemainingQuantity(),
                queueMessageRecord.getCreateMillis());
    }
}
