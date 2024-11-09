package com.ramenpedia.handler.dto;

import com.ramenpedia.entity.QueueMessageRecord;
import com.ramenpedia.enumerate.StoreBusinessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessHoursSend {
    private Long returnerId;
    private QueueMessageRecord.Type type;
    private StoreBusinessStatus status;
    private Long createMillis;
}
