package com.ramenpedia.controller.websocket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueueMessage {
    private Integer nowQueuePersonCount;
}
