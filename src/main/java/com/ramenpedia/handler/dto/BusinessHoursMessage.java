package com.ramenpedia.handler.dto;

import com.ramenpedia.enumerate.StoreBusinessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessHoursMessage {
    private StoreBusinessStatus status;
}
