package com.ramenpedia.handler.dto;

import com.ramenpedia.entity.QueueMessageRecord;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LimitedSend {
    private Long returnerId;
    private QueueMessageRecord.Type type;
    private Integer remainingQuantity;
    private Long createMillis;
}
