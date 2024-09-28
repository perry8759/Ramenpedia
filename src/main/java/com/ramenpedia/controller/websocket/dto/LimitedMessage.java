package com.ramenpedia.controller.websocket.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LimitedMessage {
    private Integer remainingQuantity;
}
