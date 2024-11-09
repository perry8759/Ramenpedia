package com.ramenpedia.handler.dto;

import com.ramenpedia.entity.QueueMessageRecord;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueueSend {
    private Long returnerId;
    private QueueMessageRecord.Type type;
    private Integer nowQueuePersonCount;
    private Long createMillis;
}
