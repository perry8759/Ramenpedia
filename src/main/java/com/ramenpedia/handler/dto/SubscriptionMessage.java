package com.ramenpedia.handler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionMessage {
    private String token;
    private Long storeId;
}
