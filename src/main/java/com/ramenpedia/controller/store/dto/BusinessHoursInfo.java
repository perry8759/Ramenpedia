package com.ramenpedia.controller.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessHoursInfo {
    private String openTime;
    private String closeTime;
}
